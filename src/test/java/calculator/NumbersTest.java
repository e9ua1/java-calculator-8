package calculator;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumbersTest {

    @Test
    void 빈_배열은_0을_반환한다() {
        Numbers numbers = new Numbers(new String[]{});
        assertThat(numbers.sum()).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void 숫자_하나의_합을_계산한다() {
        Numbers numbers = new Numbers(new String[]{"5"});
        assertThat(numbers.sum()).isEqualTo(BigInteger.valueOf(5));
    }

    @Test
    void 여러_숫자의_합을_계산한다() {
        Numbers numbers = new Numbers(new String[]{"1", "2", "3"});
        assertThat(numbers.sum()).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 큰_숫자의_합을_계산한다() {
        Numbers numbers = new Numbers(new String[]{
                "999999999999999999",
                "999999999999999999"
        });
        assertThat(numbers.sum()).isEqualTo(new BigInteger("1999999999999999998"));
    }

    @Test
    void 음수가_포함되면_예외를_발생시킨다() {
        assertThatThrownBy(() -> new Numbers(new String[]{"-1", "2", "3"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    void 잘못된_형식이_포함되면_예외를_발생시킨다() {
        assertThatThrownBy(() -> new Numbers(new String[]{"1", "abc", "3"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
