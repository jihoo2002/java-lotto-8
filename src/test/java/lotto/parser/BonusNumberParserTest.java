package lotto.parser;

import lotto.util.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BonusNumberParserTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("빈문자열 또는 null 입력 시 예외가 발생한다")
    void 빈문자열또는_null_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberParser.parse(input)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_EMPTY.error(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1a", "!"})
    @DisplayName("숫자가 아닌 값을 입력 시 예외가 발생한다")
    void 숫자아닌_입력_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberParser.parse(input)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_NOT_NUMERIC.error(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "100"})
    @DisplayName("입력값이 범위에 벗어나면 예외가 발생한다")
    void 범위벗어난_입력_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberParser.parse(input)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.error(), exception.getMessage());
    }
}
