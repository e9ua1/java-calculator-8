package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InputValidatorTest {
    @Test
    void null_입력시_false를_반환한다() {
        InputValidator validator = new InputValidator();
        boolean result = validator.isValid(null);
        assertThat(result).isFalse();
    }
}
