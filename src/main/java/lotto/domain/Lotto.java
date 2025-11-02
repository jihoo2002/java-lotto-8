package lotto.domain;

import lotto.util.ErrorMessage;
import lotto.util.LottoConstants;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_NUMBERS_PER_TICKET) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.error());
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.error());
        }
    }

    private void validateCount(List<Integer> numbers) {
        boolean hasInvalidNumber = numbers.stream()
                .anyMatch(num -> num < LottoConstants.LOTTO_MIN_NUMBER || num > LottoConstants.LOTTO_MAX_NUMBER);

        if (hasInvalidNumber) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_LOTTO_NUMBER.error());
        }
    }
}
