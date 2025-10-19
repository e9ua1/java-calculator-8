package calculator.parser;

public class SingleNumberParser implements DelimiterParser {

    @Override
    public boolean supports(String input) {
        return true;
    }

    @Override
    public String[] parse(String input) {
        return new String[]{input};
    }
}
