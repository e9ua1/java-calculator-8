package calculator;

import java.math.BigInteger;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

import calculator.parser.CustomDelimiterParser;
import calculator.parser.DefaultDelimiterParser;
import calculator.parser.DelimiterParser;
import calculator.parser.SingleNumberParser;

public class Application {

    public static void main(String[] args) {
        StringCalculator calculator = createCalculator();

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        BigInteger result = calculator.calculate(input);
        System.out.println("결과 : " + result);
    }

    private static StringCalculator createCalculator() {
        DelimiterExtractor extractor = new DelimiterExtractor();
        StringSplitter splitter = new StringSplitter();
        NumberConverter converter = new NumberConverter();
        NumberValidator validator = new NumberValidator();
        Adder adder = new Adder();

        List<DelimiterParser> parsers = List.of(
                new CustomDelimiterParser(extractor, splitter),
                new DefaultDelimiterParser(splitter),
                new SingleNumberParser()
        );

        return new StringCalculator(parsers, converter, validator, adder);
    }
}
