package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StringSplitter 테스트")
public class StringSplitterTest {

    @Test
    @DisplayName("단일 구분자로 문자열을 분리한다")
    void splitWithSingleDelimiter() {
        // given
        StringSplitter splitter = new StringSplitter();
        String input = "1,2,3";
        String delimiter = ",";
        String[] expected = new String[]{"1", "2", "3"};

        // when
        String[] result = splitter.split(input, delimiter);

        // then
        assertThat(result).containsExactly(expected);
    }

    @Test
    @DisplayName("기본 구분자로 문자열을 분리한다")
    void splitWithDefaultDelimiters() {
        // given
        StringSplitter splitter = new StringSplitter();
        String input = "1,2:3";
        String[] expected = new String[]{"1", "2", "3"};

        // when
        String[] result = splitter.split(input);

        // then
        assertThat(result).containsExactly(expected);
    }
}
