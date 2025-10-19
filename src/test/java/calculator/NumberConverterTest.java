package calculator;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberConverterTest {

    @Test
    void 문자열을_정수로_반환한다() {
        NumberConverter converter = new NumberConverter();
        BigInteger result = converter.convert("123");
        assertThat(result).isEqualTo(BigInteger.valueOf(123));
    }

    @Test
    void 공백을_제거하고_정수로_반환한다() {
        NumberConverter converter = new NumberConverter();
        BigInteger result = converter.convert(" 123 ");
        assertThat(result).isEqualTo(BigInteger.valueOf(123));
    }

    @Test
    void 매우_큰_숫자를_변환한다() {
        NumberConverter converter = new NumberConverter();
        BigInteger result = converter.convert("999999999999999999999999");
        assertThat(result).isEqualTo(new BigInteger("999999999999999999999999"));
    }

    @Test
    void 잘못된_형식_입력시_예외를_발생시킨다() {
        NumberConverter converter = new NumberConverter();
        assertThatThrownBy(() -> converter.convert("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
