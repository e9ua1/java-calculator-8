package calculator.parser;

import calculator.StringSplitter;

public class CustomDelimiterParser implements DelimiterParser {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final int CUSTOM_DELIMITER_START_INDEX = 2;
    private static final String REAL_NEWLINE = "\n";
    private static final String ESCAPED_NEWLINE = "\\n";
    private static final int REAL_NEWLINE_LENGTH = 1;
    private static final int ESCAPED_NEWLINE_LENGTH = 2;

    private final StringSplitter splitter;

    public CustomDelimiterParser(StringSplitter splitter) {
        this.splitter = splitter;
    }

    @Override
    public boolean supports(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    @Override
    public String[] parse(String input) {
        String delimiter = extractCustomDelimiter(input);
        String numbers = extractNumbers(input);
        return splitter.split(numbers, delimiter);
    }

    private String extractCustomDelimiter(String input) {
        int delimiterEndIndex = findDelimiterEndIndex(input);
        return input.substring(CUSTOM_DELIMITER_START_INDEX, delimiterEndIndex);
    }

    private String extractNumbers(String input) {
        int delimiterEndIndex = findDelimiterEndIndex(input);
        int newlineLength = getNewlineLength(input, delimiterEndIndex);
        return input.substring(delimiterEndIndex + newlineLength);
    }

    private int findDelimiterEndIndex(String input) {
        int realNewlineIndex = input.indexOf(REAL_NEWLINE);
        if (realNewlineIndex != -1) {
            return realNewlineIndex;
        }
        return input.indexOf(ESCAPED_NEWLINE);
    }

    private int getNewlineLength(String input, int index) {
        if (isEscapedNewline(input, index)) {
            return ESCAPED_NEWLINE_LENGTH;
        }
        return REAL_NEWLINE_LENGTH;
    }

    private boolean isEscapedNewline(String input, int index) {
        return index + 1 < input.length() && input.charAt(index) == '\\';
    }
}
