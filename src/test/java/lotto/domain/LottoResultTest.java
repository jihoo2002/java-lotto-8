package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class LottoResultTest {

    private WinningNumbers winningNumbers;
    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoResult = new LottoResult(winningNumbers);
    }

    @Test
    @DisplayName("생성 직후 당첨 통계가 모두 초기화되어 있어야 한다")
    void 당첨통계_초기화_검증() {
        Map<LottoRank, Integer> rankCount = lottoResult.getRankCount();

        assertThat(rankCount)
                .hasSize(5)
                .contains(
                        entry(LottoRank.THREE_MATCHES, 0),
                        entry(LottoRank.FOUR_MATCHES, 0),
                        entry(LottoRank.FIVE_MATCHES, 0),
                        entry(LottoRank.FIVE_MATCHES_BONUS, 0),
                        entry(LottoRank.SIX_MATCHES, 0)
                );
    }

    @Test
    @DisplayName("로또 목록 계산 시 당첨 통계가 정확히 반영된다")
    void 당첨통계_계산_검증() {
        Lotto lotto5th = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto lotto5thAlt = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto lotto5thBonus = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto1st = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lottoNoWin = new Lotto(List.of(1, 2, 10, 11, 12, 13));

        Lottos lottos = new Lottos(List.of(lotto5th, lotto5thAlt, lotto5thBonus, lotto1st, lottoNoWin));

        lottoResult.calculate(lottos);
        Map<LottoRank, Integer> rankCount = lottoResult.getRankCount();

        assertThat(rankCount).contains(
                entry(LottoRank.THREE_MATCHES, 1),
                entry(LottoRank.FOUR_MATCHES, 0),
                entry(LottoRank.FIVE_MATCHES, 1),
                entry(LottoRank.FIVE_MATCHES_BONUS, 1),
                entry(LottoRank.SIX_MATCHES, 1)
        );
    }

    @Test
    @DisplayName("총 구매 금액 대비 수익률이 정확히 계산된다")
    void 수익률_계산_검증() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15)),
                new Lotto(List.of(16, 17, 18, 19, 20, 21)),
                new Lotto(List.of(22, 23, 24, 25, 26, 27))
        ));
        int totalPurchase = 4000;

        lottoResult.calculate(lottos);
        double profitRate = lottoResult.calculateProfitRate(totalPurchase);

        assertThat(profitRate).isEqualTo(125.0);
    }

    @Test
    @DisplayName("당첨금이 없으면 수익률은 0을 반환한다")
    void 수익률_0_검증() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(10, 11, 12, 13, 14, 15))
        ));
        int totalPurchase = 1000;

        lottoResult.calculate(lottos);
        double profitRate = lottoResult.calculateProfitRate(totalPurchase);

        assertThat(profitRate).isEqualTo(0.0);
    }
}
