package lotto.domain;

import lotto.util.ErrorMessage;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number, Lotto winningLotto) {
        validate(number, winningLotto);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validate(int number, Lotto winningLotto) {
        if (winningLotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATE_WITH_WINNING.error());
        }
    }
}
