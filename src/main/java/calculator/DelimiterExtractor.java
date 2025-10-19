package calculator;

public class DelimiterExtractor {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final int CUSTOM_DELIMITER_START_INDEX = 2;
    private static final int REAL_NEWLINE_LENGTH = 1;
    private static final int ESCAPED_NEWLINE_LENGTH = 2;

    public boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    public String extractCustomDelimiter(String input) {
        int delimiterEndIndex = findDelimiterEndIndex(input);
        return input.substring(CUSTOM_DELIMITER_START_INDEX, delimiterEndIndex);
    }

    public String extractNumbers(String input) {
        int delimiterEndIndex = findDelimiterEndIndex(input);
        int newlineLength = getNewlineLength(input, delimiterEndIndex);
        return input.substring(delimiterEndIndex + newlineLength);
    }

    private int findDelimiterEndIndex(String input) {
        int realNewline = input.indexOf("\n");
        if (realNewline != -1) {
            return realNewline;
        }
        return input.indexOf("\\n");
    }

    private int getNewlineLength(String input, int index) {
        if (index + 1 < input.length() && input.charAt(index) == '\\') {
            return ESCAPED_NEWLINE_LENGTH;
        }
        return REAL_NEWLINE_LENGTH;
    }
}
