package lotto.parser;

import lotto.util.ErrorMessage;

import java.util.Arrays;
import java.util.List;

public class WinningNumberParser {

    private static final String DELIMITER = ",";

    public static List<Integer> parse(String input) {
        validateInput(input);
        return parseToNumbers(input);
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_EMPTY.error());
        }
    }

    private static List<Integer> parseToNumbers(String input) {
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_NOT_NUMERIC.error());
        }
    }
}
