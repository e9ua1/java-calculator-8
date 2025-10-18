package calculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
}
