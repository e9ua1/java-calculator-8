package calculator;

public class DelimiterExtractor {

    public boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }
}
