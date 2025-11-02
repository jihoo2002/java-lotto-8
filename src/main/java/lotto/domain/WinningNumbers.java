package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonus) {
        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = new BonusNumber(bonus, winningLotto);
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }

    public boolean contains(int number) {
        return winningLotto.getNumbers().contains(number);
    }
}
