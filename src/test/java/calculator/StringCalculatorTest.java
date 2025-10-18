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

    @Test
    void 쉼표_구분자로_두_개_이상의_숫자를_더한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1,2")).isEqualTo(3L);
        assertThat(calculator.calculate("1,2,3")).isEqualTo(6L);
    }

    @Test
    void 콜론_구분자로_두_개_이상의_숫자를_더한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1:2:3")).isEqualTo(6L);
    }

    @Test
    void 쉼표와_콜론을_혼합하여_사용한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1,2:3")).isEqualTo(6L);
    }
}
