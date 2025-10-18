package calculator;

public class DelimiterExtractor {

    public boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    public String extractCustomDelimiter(String input) {
        int delimiterEndIndex = findDelimiterEndIndex(input);
        return input.substring(2, delimiterEndIndex);
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
            return 2;
        }
        return 1;
    }
}
