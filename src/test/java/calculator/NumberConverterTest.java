package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumberConverterTest {
    @Test
    void 문자열을_정수로_반환한다() {
        NumberConverter converter = new Converter();
        long result = converter.convert("123");
        assertThat(result).isEqualTo(123L);
    }
}
