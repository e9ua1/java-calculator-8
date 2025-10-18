package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class CalculatorTest {
    @Test
    void 빈_배열은_0을_반환한다() {
        Calculator calculator = new Calculator();
        long result = calculator.sum(new long[]{});
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void 정수_배열의_합을_계산한다() {
        Calculator calculator = new Calculator();
        long result = calculator.sum(new long[]{1, 2, 3});
        assertThat(result).isEqualTo(6L);
    }
}
