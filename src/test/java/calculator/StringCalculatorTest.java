package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringCalculatorTest {

    @Test
    void null_또는_빈_문자열_입력시_0을_반환한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate(null)).isEqualTo(0L);
        assertThat(calculator.calculate("")).isEqualTo(0L);
    }

    @Test
    void 숫자_하나만_입력시_해당_숫자를_반환한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1")).isEqualTo(1L);
    }
}
