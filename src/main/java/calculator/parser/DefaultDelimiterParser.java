package calculator.parser;

import calculator.StringSplitter;

public class DefaultDelimiterParser implements DelimiterParser {

    private static final String COMMA = ",";
    private static final String COLON = ":";

    private final StringSplitter splitter;

    public DefaultDelimiterParser(StringSplitter splitter) {
        this.splitter = splitter;
    }

    @Override
    public boolean supports(String input) {
        return input.contains(COMMA) || input.contains(COLON);
    }

    @Override
    public String[] parse(String input) {
        return splitter.split(input);
    }
}
