package calculator;

import java.math.BigInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Number 도메인 객체 테스트")
public class NumberTest {

    @Test
    @DisplayName("문자열을 Number로 변환한다")
    void convertStringToNumber() {
        // given
        String input = "123";
        BigInteger expected = BigInteger.valueOf(123);

        // when
        Number number = new Number(input);

        // then
        assertThat(number.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("공백을 제거하고 Number로 변환한다")
    void convertStringWithWhitespaceToNumber() {
        // given
        String input = " 123 ";
        BigInteger expected = BigInteger.valueOf(123);

        // when
        Number number = new Number(input);

        // then
        assertThat(number.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("매우 큰 숫자를 Number로 변환한다")
    void convertLargeNumberString() {
        // given
        String input = "999999999999999999999999";
        BigInteger expected = new BigInteger("999999999999999999999999");

        // when
        Number number = new Number(input);

        // then
        assertThat(number.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("음수 입력 시 예외를 발생시킨다")
    void throwExceptionForNegativeNumber() {
        // given
        String input = "-1";

        // when & then
        assertThatThrownBy(() -> new Number(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    @DisplayName("잘못된 형식 입력 시 예외를 발생시킨다")
    void throwExceptionForInvalidFormat() {
        // given
        String input = "abc";

        // when & then
        assertThatThrownBy(() -> new Number(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }

    @Test
    @DisplayName("0은 유효한 Number이다")
    void zeroIsValidNumber() {
        // given
        String input = "0";
        BigInteger expected = BigInteger.ZERO;

        // when
        Number number = new Number(input);

        // then
        assertThat(number.getValue()).isEqualTo(expected);
    }
}
