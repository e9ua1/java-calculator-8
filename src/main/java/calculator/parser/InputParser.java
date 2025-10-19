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
                // FallbackParser에 의해 항상 처리되지만, 체인 구성 변경 시를 대비한 방어 코드
                .orElseThrow(() -> new IllegalArgumentException("입력 형식이 올바르지 않습니다"));
    }
}
