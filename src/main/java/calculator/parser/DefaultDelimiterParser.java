package calculator.parser;

import calculator.StringSplitter;

public class DefaultDelimiterParser implements DelimiterParser {

    private final StringSplitter splitter;

    public DefaultDelimiterParser(StringSplitter splitter) {
        this.splitter = splitter;
    }

    @Override
    public boolean supports(String input) {
        return input.contains(",") || input.contains(":");
    }

    @Override
    public String[] parse(String input) {
        return splitter.split(input);
    }
}
