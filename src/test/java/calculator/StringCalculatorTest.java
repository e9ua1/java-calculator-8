package calculator;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DefaultDelimiterParser;
import calculator.parser.DelimiterParser;
import calculator.parser.FallbackParser;
import calculator.parser.InputParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("문자열 계산기 테스트")
public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        StringSplitter splitter = new StringSplitter();

        List<DelimiterParser> parsers = List.of(
                new CustomDelimiterParser(splitter),
                new DefaultDelimiterParser(splitter),
                new FallbackParser()
        );

        InputParser parser = new InputParser(parsers);
        calculator = new StringCalculator(parser);
    }

    @Test
    @DisplayName("null 또는 빈 문자열 입력 시 0을 반환한다")
    void returnZeroForNullOrEmptyInput() {
        // given
        String nullInput = null;
        String emptyInput = "";

        // when & then
        assertAll(
                () -> assertThat(calculator.calculate(nullInput)).isEqualTo(BigInteger.ZERO),
                () -> assertThat(calculator.calculate(emptyInput)).isEqualTo(BigInteger.ZERO)
        );
    }

    @Test
    @DisplayName("숫자 하나만 입력 시 해당 숫자를 반환한다")
    void returnNumberForSingleInput() {
        // given
        String input = "1";
        BigInteger expected = BigInteger.valueOf(1);

        // when
        BigInteger result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("쉼표 구분자로 두 개 이상의 숫자를 더한다")
    void addNumbersSeparatedByComma() {
        // given
        String input1 = "1,2";
        String input2 = "1,2,3";

        // when & then
        assertAll(
                () -> assertThat(calculator.calculate(input1)).isEqualTo(BigInteger.valueOf(3)),
                () -> assertThat(calculator.calculate(input2)).isEqualTo(BigInteger.valueOf(6))
        );
    }

    @Test
    @DisplayName("콜론 구분자로 두 개 이상의 숫자를 더한다")
    void addNumbersSeparatedByColon() {
        // given
        String input = "1:2:3";
        BigInteger expected = BigInteger.valueOf(6);

        // when
        BigInteger result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("쉼표와 콜론을 혼합하여 사용한다")
    void addNumbersWithMixedDelimiters() {
        // given
        String input = "1,2:3";
        BigInteger expected = BigInteger.valueOf(6);

        // when
        BigInteger result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("커스텀 구분자를 사용한다")
    void addNumbersWithCustomDelimiter() {
        // given
        String input = "//;\n1;2;3";
        BigInteger expected = BigInteger.valueOf(6);

        // when
        BigInteger result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("이스케이프된 개행문자로 커스텀 구분자를 사용한다")
    void addNumbersWithEscapedNewlineCustomDelimiter() {
        // given
        String input = "//;\\n1;2;3";
        BigInteger expected = BigInteger.valueOf(6);

        // when
        BigInteger result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("매우 큰 숫자를 계산한다")
    void calculateLargeNumbers() {
        // given
        String input = "999999999999999999,1";
        BigInteger expected = new BigInteger("1000000000000000000");

        // when
        BigInteger result = calculator.calculate(input);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("음수 입력 시 예외를 발생시킨다")
    void throwExceptionForNegativeNumber() {
        // given
        String input = "-1,2,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    @DisplayName("잘못된 형식 입력 시 예외를 발생시킨다")
    void throwExceptionForInvalidFormat() {
        // given
        String input = "1,a,3";

        // when & then
        assertThatThrownBy(() -> calculator.calculate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
