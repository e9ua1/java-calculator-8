package calculator;

public class StringSplitter {

    private static final String DEFAULT_DELIMITERS = ",|:";

    public String[] split(String input, String delimiter) {
        return input.split(delimiter);
    }

    public String[] splitByDefaultDelimiters(String input) {
        return input.split(DEFAULT_DELIMITERS);
    }
}
