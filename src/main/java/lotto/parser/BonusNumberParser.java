package lotto.parser;

import lotto.util.ErrorMessage;
import lotto.util.LottoConstants;

public class BonusNumberParser {

    public static int parse(String input) {
        validateNotBlank(input);
        int number = parseNumeric(input);
        validateRange(number);
        return number;
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_EMPTY.error());
        }
    }

    private static int parseNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_NOT_NUMERIC.error());
        }
    }

    private static void validateRange(int number) {
        if (number < LottoConstants.LOTTO_MIN_NUMBER || number > LottoConstants.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE.error());
        }
    }
}
