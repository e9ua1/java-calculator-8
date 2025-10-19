package calculator.parser;

public interface DelimiterParser {

    boolean supports(String input);
    
    String[] parse(String input);
}
