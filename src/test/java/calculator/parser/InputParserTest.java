package calculator.parser;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.DelimiterExtractor;
import calculator.StringSplitter;

import static org.assertj.core.api.Assertions.assertThat;

public class InputParserTest {

    private InputParser parser;

    @BeforeEach
    void setUp() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        StringSplitter splitter = new StringSplitter();

        List<DelimiterParser> parsers = List.of(
                new CustomDelimiterParser(extractor, splitter),
                new DefaultDelimiterParser(splitter),
                new SingleNumberParser()
        );

        parser = new InputParser(parsers);
    }

    @Test
    void 쉼표로_구분된_문자열을_파싱한다() {
        String[] result = parser.parse("1,2,3");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 콜론으로_구분된_문자열을_파싱한다() {
        String[] result = parser.parse("1:2:3");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 쉼표와_콜론_혼합_문자열을_파싱한다() {
        String[] result = parser.parse("1,2:3");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 커스텀_구분자로_구분된_문자열을_파싱한다() {
        String[] result = parser.parse("//;\n1;2;3");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 단일_숫자를_파싱한다() {
        String[] result = parser.parse("5");
        assertThat(result).containsExactly("5");
    }
}
