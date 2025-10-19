package calculator;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberValidatorTest {

    @Test
    void 음수_입력시_예외를_발생시킨다() {
        NumberValidator validator = new NumberValidator();
        assertThatThrownBy(() -> validator.validate(BigInteger.valueOf(-1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    void 양수는_검증을_통과한다() {
        NumberValidator validator = new NumberValidator();
        validator.validate(BigInteger.ZERO);
        validator.validate(BigInteger.valueOf(1));
        validator.validate(new BigInteger("999999999999999999"));
    }
}
