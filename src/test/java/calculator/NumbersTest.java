package calculator;

import java.math.BigInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Numbers 일급 컬렉션 테스트")
public class NumbersTest {

    @Test
    @DisplayName("빈 배열은 0을 반환한다")
    void returnZeroForEmptyArray() {
        // given
        String[] input = new String[]{};

        // when
        Numbers numbers = Numbers.from(input);

        // then
        assertThat(numbers.sum()).isEqualTo(BigInteger.ZERO);
    }

    @Test
    @DisplayName("숫자 하나의 합을 계산한다")
    void calculateSumOfSingleNumber() {
        // given
        String[] input = new String[]{"5"};
        BigInteger expected = BigInteger.valueOf(5);

        // when
        Numbers numbers = Numbers.from(input);

        // then
        assertThat(numbers.sum()).isEqualTo(expected);
    }

    @Test
    @DisplayName("여러 숫자의 합을 계산한다")
    void calculateSumOfMultipleNumbers() {
        // given
        String[] input = new String[]{"1", "2", "3"};
        BigInteger expected = BigInteger.valueOf(6);

        // when
        Numbers numbers = Numbers.from(input);

        // then
        assertThat(numbers.sum()).isEqualTo(expected);
    }

    @Test
    @DisplayName("큰 숫자의 합을 계산한다")
    void calculateSumOfLargeNumbers() {
        // given
        String[] input = new String[]{"999999999999999999", "999999999999999999"};
        BigInteger expected = new BigInteger("1999999999999999998");

        // when
        Numbers numbers = Numbers.from(input);

        // then
        assertThat(numbers.sum()).isEqualTo(expected);
    }

    @Test
    @DisplayName("음수가 포함되면 예외를 발생시킨다")
    void throwExceptionWhenNegativeNumberIncluded() {
        // given
        String[] input = new String[]{"-1", "2", "3"};

        // when & then
        assertThatThrownBy(() -> Numbers.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    @DisplayName("잘못된 형식이 포함되면 예외를 발생시킨다")
    void throwExceptionWhenInvalidFormatIncluded() {
        // given
        String[] input = new String[]{"1", "abc", "3"};

        // when & then
        assertThatThrownBy(() -> Numbers.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
