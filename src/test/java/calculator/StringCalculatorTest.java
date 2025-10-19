package calculator;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DefaultDelimiterParser;
import calculator.parser.DelimiterParser;
import calculator.parser.InputParser;
import calculator.parser.SingleNumberParser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        StringSplitter splitter = new StringSplitter();

        List<DelimiterParser> parsers = List.of(
                new CustomDelimiterParser(extractor, splitter),
                new DefaultDelimiterParser(splitter),
                new SingleNumberParser()
        );

        InputParser parser = new InputParser(parsers);
        calculator = new StringCalculator(parser);
    }

    @Test
    void null_또는_빈_문자열_입력시_0을_반환한다() {
        assertThat(calculator.calculate(null)).isEqualTo(BigInteger.ZERO);
        assertThat(calculator.calculate("")).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void 숫자_하나만_입력시_해당_숫자를_반환한다() {
        assertThat(calculator.calculate("1")).isEqualTo(BigInteger.valueOf(1));
    }

    @Test
    void 쉼표_구분자로_두_개_이상의_숫자를_더한다() {
        assertThat(calculator.calculate("1,2")).isEqualTo(BigInteger.valueOf(3));
        assertThat(calculator.calculate("1,2,3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 콜론_구분자로_두_개_이상의_숫자를_더한다() {
        assertThat(calculator.calculate("1:2:3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 쉼표와_콜론을_혼합하여_사용한다() {
        assertThat(calculator.calculate("1,2:3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 커스텀_구분자를_사용한다() {
        assertThat(calculator.calculate("//;\n1;2;3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 이스케이프된_개행문자로_커스텀_구분자를_사용한다() {
        assertThat(calculator.calculate("//;\\n1;2;3")).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    void 매우_큰_숫자를_계산한다() {
        BigInteger result = calculator.calculate("999999999999999999,1");
        assertThat(result).isEqualTo(new BigInteger("1000000000000000000"));
    }

    @Test
    void 음수_입력시_예외를_발생시킨다() {
        assertThatThrownBy(() -> calculator.calculate("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음수는 허용되지 않습니다");
    }

    @Test
    void 잘못된_형식_입력시_예외를_발생시킨다() {
        assertThatThrownBy(() -> calculator.calculate("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자 형식이 올바르지 않습니다");
    }
}
