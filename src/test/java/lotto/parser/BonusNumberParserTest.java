package lotto.parser;

import lotto.util.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BonusNumberParserTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 빈문자열또는_null_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberParser.parse(input)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_EMPTY.error(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1a", "!"})
    void 숫자아닌_입력_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberParser.parse(input)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_NOT_NUMERIC.error(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "100"})
    void 범위벗어난_입력_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberParser.parse(input)
        );

        assertEquals(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.error(), exception.getMessage());
    }
}
