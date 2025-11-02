package lotto.test;

import lotto.util.LottoNumberGenerator;

import java.util.List;

public class FixValueGenerator implements LottoNumberGenerator {

    private final List<Integer> numbers;

    public FixValueGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generate() {
        return numbers;
    }
}
