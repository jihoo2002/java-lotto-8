package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.util.LottoConstants;
import lotto.util.LottoNumberGenerator;

import java.util.List;

public class RandomValueGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoConstants.LOTTO_MIN_NUMBER,
                LottoConstants.LOTTO_MAX_NUMBER,
                LottoConstants.LOTTO_NUMBERS_PER_TICKET
        );
    }
}
