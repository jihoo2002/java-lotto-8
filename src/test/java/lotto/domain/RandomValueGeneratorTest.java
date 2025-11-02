package lotto.domain;

import lotto.util.LottoConstants;
import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class RandomValueGeneratorTest {

    private LottoNumberGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new RandomValueGenerator();
    }

    @Test
    @DisplayName("로또 번호 생성 시 6개의 숫자를 반환한다")
    void 정해진_개수만큼_숫자를_반환한다() {
        List<Integer> numbers = generator.generate();

        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("정해진 범위(MIN ~ MAX) 내의 숫자를 반환한다")
    void 정해진_범위_내의_숫자를_반환한다() {
        List<Integer> numbers = generator.generate();

        assertThat(numbers).allMatch(number ->
                number >= LottoConstants.LOTTO_MIN_NUMBER &&
                        number <= LottoConstants.LOTTO_MAX_NUMBER
        );
    }

    @Test
    @DisplayName("중복되지 않는 숫자를 반환한다")
    void 중복되지_않는_숫자를_반환한다() {
        List<Integer> numbers = generator.generate();
        long distinctCount = numbers.stream().distinct().count();

        assertThat(distinctCount).isEqualTo(numbers.size());
    }
}
