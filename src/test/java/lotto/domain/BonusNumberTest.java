package lotto.domain;

import lotto.util.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 정상 생성된다")
    void 보너스번호_중복없으면_정상생성() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        BonusNumber bonusNumber = new BonusNumber(7, winningLotto);

        assertEquals(7, bonusNumber.getNumber());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void 보너스번호_중복시_예외발생() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BonusNumber(3, winningLotto)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_DUPLICATE_WITH_WINNING.error(), exception.getMessage());
    }
}
