package lotto.domain;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final int ROUNDING_SCALE = 100;
    private static final int INITIAL_RANK_COUNT = 0;
    private static final int INCREMENT = 1;

    private final WinningNumbers winningNumbers;
    private final Map<LottoRank, Integer> rankCount = new EnumMap<>(LottoRank.class);

    public LottoResult(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
        initializeRankCount();
    }

    public void calculate(Lottos lottos) {
        lottos.getLottos().stream()
                .map(this::determineRank)
                .filter(rank -> rank != LottoRank.NO_WINNER)
                .forEach(rank -> rankCount.put(rank, rankCount.get(rank) + INCREMENT));
    }

    public Map<LottoRank, Integer> getRankCount() {
        return new LinkedHashMap<>(rankCount);
    }

    public double calculateProfitRate(int totalPurchaseAmount) {
        double totalPrize = rankCount.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        double profitRate = totalPrize * PERCENTAGE_MULTIPLIER / totalPurchaseAmount;
        return roundToTwoDecimal(profitRate);
    }

    private void initializeRankCount() {
        for (LottoRank rank : LottoRank.values()) {
            if (rank != LottoRank.NO_WINNER) {
                rankCount.put(rank, INITIAL_RANK_COUNT);
            }
        }
    }

    private double roundToTwoDecimal(double value) {
        return Math.round(value * ROUNDING_SCALE) / (double) ROUNDING_SCALE;
    }

    private LottoRank determineRank(Lotto lotto) {
        long matchCount = lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();

        boolean matchBonus = lotto.getNumbers().contains(winningNumbers.getBonusNumber());

        return LottoRank.getLottoRank((int) matchCount, matchBonus);
    }
}
