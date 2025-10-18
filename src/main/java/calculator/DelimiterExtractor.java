package calculator;

public class DelimiterExtractor {

    public boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    public String extractCustomDelimiter(String input) {
        int delimiterEndIndex = input.indexOf("\n");
        return input.substring(2, delimiterEndIndex);
    }
}
