package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;

class WinningNumbersTest {

    private WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        winningNumbers = new WinningNumbers(numbers, bonus);
    }

    @Test
    @DisplayName("보너스 번호를 올바르게 반환한다")
    void 보너스번호_검증() {
        assertThat(winningNumbers.getBonusNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("당첨 번호 포함 여부를 올바르게 반환한다")
    void 당첨번호_포함여부_검증() {
        assertThat(winningNumbers.contains(3)).isTrue();
        assertThat(winningNumbers.contains(8)).isFalse();
        assertThat(winningNumbers.contains(7)).isFalse();
    }
}
