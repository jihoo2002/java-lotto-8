package lotto.parser;

import lotto.util.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningNumberParserTest {

    @Test
    @DisplayName("올바른 입력 문자열은 숫자 리스트로 변환된다")
    void 정상입력_숫자리스트_반환() {
        List<Integer> numbers = WinningNumberParser.parse("1,2,3,4,5,6");
        assertEquals(List.of(1, 2, 3, 4, 5, 6), numbers);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력이 null이거나 빈 문자열이면 예외가 발생한다")
    void null또는빈문자열_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> WinningNumberParser.parse(input)
        );
        assertEquals(ErrorMessage.INPUT_EMPTY.error(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,a,5", "1,2,,4", "1,2,3,4,b"})
    @DisplayName("숫자가 아닌 값이 포함되면 예외가 발생한다")
    void 숫자아닌값_예외발생(String input) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> WinningNumberParser.parse(input)
        );
        assertEquals(ErrorMessage.WINNING_NUMBERS_NOT_NUMERIC.error(), exception.getMessage());
    }
}
