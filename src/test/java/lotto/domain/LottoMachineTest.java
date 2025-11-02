package lotto.domain;

import lotto.test.FixValueGenerator;
import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoMachineTest {

    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        List<Integer> fixedNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoNumberGenerator testGenerator = new FixValueGenerator(fixedNumbers);
        lottoMachine = new LottoMachine(testGenerator);
    }

    @Test
    @DisplayName("고정 번호 생성기를 사용하면 자동 로또는 항상 같은 번호로 오름차순 생성된다")
    void 고정번호자동로또_생성_및_검증() {
        int money = 3000;
        Lottos result = lottoMachine.buyAutoLottos(money);

        assertEquals(3, result.getLottos().size());

        result.getLottos().forEach(lotto ->
                assertEquals(List.of(1, 2, 3, 4, 5, 6), lotto.getNumbers()));
    }
}
