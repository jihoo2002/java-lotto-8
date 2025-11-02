package lotto.domain;

import lotto.util.LottoConstants;
import lotto.util.LottoNumberGenerator;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int START_INDEX = 0;
    private final LottoNumberGenerator generator;

    public LottoMachine(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos buyAutoLottos(int money) {
        int count = money / LottoConstants.LOTTO_TICKET_PRICE;

        List<Lotto> lottoList = IntStream.range(START_INDEX, count)
                .mapToObj(i -> createSortedLotto())
                .toList();

        return new Lottos(lottoList);
    }

    private Lotto createSortedLotto() {
        List<Integer> numbers = generator.generate().stream()
                .sorted()
                .toList();

        return new Lotto(numbers);
    }
}
