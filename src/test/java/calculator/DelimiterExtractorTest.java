package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DelimiterExtractorTest {

    @Test
    void 커스텀_구분자가_있는지_확인한다() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        assertThat(extractor.hasCustomDelimiter("//;\n1;2")).isTrue();
        assertThat(extractor.hasCustomDelimiter("1,2")).isFalse();
    }

    @Test
    void 커스텀_구분자를_추출한다() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        assertThat(extractor.extractCustomDelimiter("//;\n1;2")).isEqualTo(";");
        assertThat(extractor.extractCustomDelimiter("//|\n1|2")).isEqualTo("|");
    }

    @Test
    void 커스텀_구분자를_제외한_숫자_부분을_추출한다() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        assertThat(extractor.extractNumbers("//;\n1;2;3")).isEqualTo("1;2;3");
    }

    @Test
    void 이스케이프된_개행문자로_커스텀_구분자를_추출한다() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        assertThat(extractor.extractCustomDelimiter("//;\\n1;2")).isEqualTo(";");
        assertThat(extractor.extractCustomDelimiter("//|\\n1|2")).isEqualTo("|");
    }

    @Test
    void 이스케이프된_개행문자로_숫자_부분을_추출한다() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        assertThat(extractor.extractNumbers("//;\\n1;2;3")).isEqualTo("1;2;3");
    }
}
