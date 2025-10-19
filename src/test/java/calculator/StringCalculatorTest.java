package calculator;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StringCalculatorTest {

    @Test
    void null_또는_빈_문자열_입력시_0을_반환한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate(null)).isEqualTo(BigInteger.ZERO);
        assertThat(calculator.calculate("")).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void 숫자_하나만_입력시_해당_숫자를_반환한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1")).isEqualTo(BigInteger.valueOf(1));
    }

    @Test
    void 쉼표_구분자로_두_개_이상의_숫자를_더한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1,2")).isEqualTo(BigInteger.valueOf(3));
        assertThat(calculator.calculate("1,2,3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 콜론_구분자로_두_개_이상의_숫자를_더한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1:2:3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 쉼표와_콜론을_혼합하여_사용한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("1,2:3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 커스텀_구분자를_사용한다() {
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.calculate("//;\n1;2;3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 매우_큰_숫자를_계산한다() {
        StringCalculator calculator = new StringCalculator();
        BigInteger result = calculator.calculate("999999999999999999,1");
        assertThat(result).isEqualTo(new BigInteger("1000000000000000000"));
    }

    @Test
    void 음수_입력시_예외를_발생시킨다() {
        StringCalculator calculator = new StringCalculator();
        assertThatThrownBy(() -> calculator.calculate("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    void 잘못된_형식_입력시_예외를_발생시킨다() {
        StringCalculator calculator = new StringCalculator();
        assertThatThrownBy(() -> calculator.calculate("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
