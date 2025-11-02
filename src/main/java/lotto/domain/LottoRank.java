package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    THREE_MATCHES(3, false, 5_000, "3개 일치"),
    FOUR_MATCHES(4, false, 50_000, "4개 일치"),
    FIVE_MATCHES(5, false, 1_500_000, "5개 일치"),
    FIVE_MATCHES_BONUS(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    SIX_MATCHES(6, false, 2_000_000_000, "6개 일치"),
    NO_WINNER(0, false, 0, "꽝");

    private final int matchCount;
    private final boolean matchBonus;
    private final int price;
    private final String description;

    LottoRank(int matchCount, boolean matchBonus, int price, String description) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.price = price;
        this.description = description;
    }

    public static LottoRank getLottoRank(int matchCount, boolean matchBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.isMatchBonus(matchCount, matchBonus))
                .findFirst()
                .orElse(NO_WINNER);
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    private boolean isMatchBonus(int matchCount, boolean matchBonus) {
        return this.matchCount == matchCount && this.matchBonus == matchBonus;
    }
}
