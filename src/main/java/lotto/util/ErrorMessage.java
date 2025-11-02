package lotto.util;

public enum ErrorMessage {

    INPUT_EMPTY("입력값이 비어 있습니다."),

    PURCHASE_AMOUNT_NOT_NUMERIC("구매 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_NOT_POSITIVE("구매 금액은 양수여야 합니다."),
    PURCHASE_AMOUNT_INVALID_UNIT("구매 금액은 1000원 단위로 입력되어야 합니다."),

    WINNING_NUMBERS_NOT_NUMERIC("당첨 로또 번호는 숫자만 입력할 수 있습니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("로또 번호에 중복된 숫자가 있습니다."),
    OUT_OF_RANGE_LOTTO_NUMBER("로또 번호는 1부터 45 사이여야 합니다."),

    BONUS_NUMBER_EMPTY("보너스 번호가 비어 있습니다."),
    BONUS_NUMBER_NOT_NUMERIC("보너스 번호는 숫자만 입력할 수 있습니다."),
    BONUS_NUMBER_OUT_OF_RANGE("보너스 번호는 1부터 45 사이여야 합니다."),
    BONUS_NUMBER_DUPLICATE_WITH_WINNING("보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String error() {
        return "[ERROR] " + message;
    }
}
