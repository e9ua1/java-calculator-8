package calculator.parser;

import calculator.DelimiterExtractor;
import calculator.StringSplitter;

public class CustomDelimiterParser implements DelimiterParser {

    private final DelimiterExtractor extractor;
    private final StringSplitter splitter;

    public CustomDelimiterParser(DelimiterExtractor extractor, StringSplitter splitter) {
        this.extractor = extractor;
        this.splitter = splitter;
    }

    @Override
    public boolean supports(String input) {
        return extractor.hasCustomDelimiter(input);
    }

    @Override
    public String[] parse(String input) {
        String delimiter = extractor.extractCustomDelimiter(input);
        String numbers = extractor.extractNumbers(input);
        return splitter.split(numbers, delimiter);
    }
}
