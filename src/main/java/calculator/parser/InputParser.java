package calculator.parser;

import java.util.List;

public class InputParser {

    private final List<DelimiterParser> parsers;

    public InputParser(List<DelimiterParser> parsers) {
        this.parsers = parsers;
    }

    public String[] parse(String input) {
        return parsers.stream()
                .filter(parser -> parser.supports(input))
                .findFirst()
                .map(parser -> parser.parse(input))
                .orElseThrow(() -> new IllegalArgumentException("입력 형식이 올바르지 않습니다"));
    }

}
