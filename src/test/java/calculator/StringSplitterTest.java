package calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class StringSplitterTest {

    @Test
    void 단일_구분자로_문자열을_분리한다() {
        StringSplitter splitter = new StringSplitter();
        String[] result = splitter.split("1,2,3", ",");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    void 기본_구분자로_문자열을_분리한다() {
        StringSplitter splitter = new StringSplitter();
        String[] result = splitter.splitByDefaultDelimiters("1,2:3");
        assertThat(result).containsExactly("1", "2", "3");
    }
}
