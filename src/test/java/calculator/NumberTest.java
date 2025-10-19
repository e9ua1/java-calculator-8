package calculator;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberTest {

    @Test
    void 문자열을_Number로_변환한다() {
        Number number = new Number("123");
        assertThat(number.getValue()).isEqualTo(BigInteger.valueOf(123));
    }

    @Test
    void 공백을_제거하고_Number로_변환한다() {
        Number number = new Number(" 123 ");
        assertThat(number.getValue()).isEqualTo(BigInteger.valueOf(123));
    }

    @Test
    void 매우_큰_숫자를_Number로_변환한다() {
        Number number = new Number("999999999999999999999999");
        assertThat(number.getValue()).isEqualTo(new BigInteger("999999999999999999999999"));
    }

    @Test
    void 음수_입력시_예외를_발생시킨다() {
        assertThatThrownBy(() -> new Number("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    void 잘못된_형식_입력시_예외를_발생시킨다() {
        assertThatThrownBy(() -> new Number("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }

    @Test
    void 영은_유효한_Number이다() {
        Number number = new Number("0");
        assertThat(number.getValue()).isEqualTo(BigInteger.ZERO);
    }
}
