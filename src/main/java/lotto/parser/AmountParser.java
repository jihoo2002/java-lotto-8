package lotto.parser;

import lotto.util.ErrorMessage;
import lotto.util.LottoConstants;

public class AmountParser {

    public static int validate(String input) {
        int amount = parsePurchaseAmount(input);
        validatePurchaseAmount(amount);
        return amount;
    }

    private static int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_NOT_NUMERIC.error());
        }
    }

    private static void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_NOT_POSITIVE.error());
        }
        if (purchaseAmount % LottoConstants.LOTTO_TICKET_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_INVALID_UNIT.error());
        }
    }
}
