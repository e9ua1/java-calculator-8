package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputValidatorTest {
    @Test
    void null_입력시_false를_반환한다() {
        InputValidator validator = new InputValidator();
        boolean result = validator.isValid(null);
        assertThat(result).isFalse();
    }

    @Test
    void 빈_문자열은_유효하다() {
        InputValidator validator = new InputValidator();
        boolean result = validator.isValid("");
        assertThat(result).isTrue();
    }

    @Test
    void 음수_입력시_예외를_발생시킨다() {
        InputValidator validator = new InputValidator();
        assertThatThrownBy(() -> validator.validateNumber(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }
}
