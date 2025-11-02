package lotto.parser;

import lotto.util.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountParserTest {

    @Test
    @DisplayName("유효한 금액이면 그대로 반환된다")
    void 유효한_금액_검증() {
        int amount = AmountParser.validate("3000");
        assertEquals(3000, amount, "유효한 금액은 그대로 반환되어야 한다");
    }

    @Test
    @DisplayName("숫자가 아닌 입력이면 예외가 발생한다")
    void 숫자아닌_입력_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AmountParser.validate("abc")
        );
        assertEquals(ErrorMessage.PURCHASE_AMOUNT_NOT_NUMERIC.error(), exception.getMessage());
    }

    @Test
    @DisplayName("0 또는 음수 입력이면 예외가 발생한다")
    void 음수또는0_입력_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AmountParser.validate("-1000")
        );
        assertEquals(ErrorMessage.PURCHASE_AMOUNT_NOT_POSITIVE.error(), exception.getMessage());
    }

    @Test
    @DisplayName("로또 티켓 단위가 아닌 금액 입력 시 예외가 발생한다")
    void 단위맞지않는_금액_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AmountParser.validate("2500")
        );
        assertEquals(ErrorMessage.PURCHASE_AMOUNT_INVALID_UNIT.error(), exception.getMessage());
    }
}
