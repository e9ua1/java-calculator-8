package calculator;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumbersTest {

    @Test
    void 빈_리스트는_0을_반환한다() {
        Numbers numbers = new Numbers(List.of());
        assertThat(numbers.sum()).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void 숫자_하나의_합을_계산한다() {
        Numbers numbers = new Numbers(List.of(new Number("5")));
        assertThat(numbers.sum()).isEqualTo(BigInteger.valueOf(5));
    }

    @Test
    void 여러_숫자의_합을_계산한다() {
        Numbers numbers = new Numbers(List.of(
                new Number("1"),
                new Number("2"),
                new Number("3")
        ));
        assertThat(numbers.sum()).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 큰_숫자의_합을_계산한다() {
        Numbers numbers = new Numbers(List.of(
                new Number("999999999999999999"),
                new Number("999999999999999999")
        ));
        assertThat(numbers.sum()).isEqualTo(new BigInteger("1999999999999999998"));
    }

    @Test
    void 음수가_포함되면_예외를_발생시킨다() {
        assertThatThrownBy(() -> new Numbers(List.of(
                new Number("-1"),
                new Number("2"),
                new Number("3")
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    void 잘못된_형식이_포함되면_예외를_발생시킨다() {
        assertThatThrownBy(() -> new Numbers(List.of(
                new Number("1"),
                new Number("abc"),
                new Number("3")
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
