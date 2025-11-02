package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoRankTest {
    @Test
    @DisplayName("일치 개수와 보너스 여부에 따라 올바른 등수가 반환된다")
    void 일치개수_보너스_따라_등수_반환() {
        assertEquals(LottoRank.THREE_MATCHES, LottoRank.getLottoRank(3, false));
        assertEquals(LottoRank.FOUR_MATCHES, LottoRank.getLottoRank(4, false));
        assertEquals(LottoRank.FIVE_MATCHES, LottoRank.getLottoRank(5, false));
        assertEquals(LottoRank.FIVE_MATCHES_BONUS, LottoRank.getLottoRank(5, true));
        assertEquals(LottoRank.SIX_MATCHES, LottoRank.getLottoRank(6, false));
    }

    @Test
    @DisplayName("정의되지 않은 조합이면 NO_WINNER가 반환된다")
    void 정의되지않은_조합_NO_WINNER_반환() {
        assertEquals(LottoRank.NO_WINNER, LottoRank.getLottoRank(2, false));
        assertEquals(LottoRank.NO_WINNER, LottoRank.getLottoRank(1, true));
    }
}
