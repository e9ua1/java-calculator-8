package calculator;

import java.util.List;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DefaultDelimiterParser;
import calculator.parser.DelimiterParser;
import calculator.parser.InputParser;
import calculator.parser.FallbackParser;

public class CalculatorFactory {

    public StringCalculator createCalculator() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        StringSplitter splitter = new StringSplitter();

        List<DelimiterParser> parsers = List.of(
                new CustomDelimiterParser(extractor, splitter),
                new DefaultDelimiterParser(splitter),
                new FallbackParser()
        );

        InputParser parser = new InputParser(parsers);
        return new StringCalculator(parser);
    }
}
