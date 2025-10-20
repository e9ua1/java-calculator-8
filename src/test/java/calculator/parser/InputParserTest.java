package calculator.parser;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import calculator.StringSplitter;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("InputParser 테스트")
public class InputParserTest {

    private InputParser parser;

    @BeforeEach
    void setUp() {
        StringSplitter splitter = new StringSplitter();

        List<DelimiterParser> parsers = List.of(
                new CustomDelimiterParser(splitter),
                new DefaultDelimiterParser(splitter),
                new FallbackParser()
        );

        parser = new InputParser(parsers);
    }

    @Test
    @DisplayName("쉼표로 구분된 문자열을 파싱한다")
    void parseCommaSeparatedString() {
        // given
        String input = "1,2,3";
        String[] expected = new String[]{"1", "2", "3"};

        // when
        String[] result = parser.parse(input);

        // then
        assertThat(result).containsExactly(expected);
    }

    @Test
    @DisplayName("콜론으로 구분된 문자열을 파싱한다")
    void parseColonSeparatedString() {
        // given
        String input = "1:2:3";
        String[] expected = new String[]{"1", "2", "3"};

        // when
        String[] result = parser.parse(input);

        // then
        assertThat(result).containsExactly(expected);
    }

    @Test
    @DisplayName("쉼표와 콜론 혼합 문자열을 파싱한다")
    void parseMixedDelimiterString() {
        // given
        String input = "1,2:3";
        String[] expected = new String[]{"1", "2", "3"};

        // when
        String[] result = parser.parse(input);

        // then
        assertThat(result).containsExactly(expected);
    }

    @Test
    @DisplayName("커스텀 구분자로 구분된 문자열을 파싱한다")
    void parseCustomDelimiterString() {
        // given
        String input = "//;\n1;2;3";
        String[] expected = new String[]{"1", "2", "3"};

        // when
        String[] result = parser.parse(input);

        // then
        assertThat(result).containsExactly(expected);
    }

    @Test
    @DisplayName("단일 숫자를 파싱한다")
    void parseSingleNumber() {
        // given
        String input = "5";
        String[] expected = new String[]{"5"};

        // when
        String[] result = parser.parse(input);

        // then
        assertThat(result).containsExactly(expected);
    }
}
