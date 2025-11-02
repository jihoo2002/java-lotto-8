package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    private Lottos lottos;
    private Lotto lowNumbersLotto;
    private Lotto highNumbersLotto;

    @BeforeEach
    void setUp() {
        lowNumbersLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        highNumbersLotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        lottos = new Lottos(List.of(lowNumbersLotto, highNumbersLotto));
    }

    @Test
    @DisplayName("Lottos 객체는 로또 목록의 크기를 정확히 반환한다")
    void 로또목록_크기_검증() {
        assertThat(lottos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("getLottos()는 저장된 로또 리스트를 반환한다")
    void 저장된로또목록_검증() {
        assertThat(lottos.getLottos()).containsExactly(lowNumbersLotto, highNumbersLotto);
    }
}
