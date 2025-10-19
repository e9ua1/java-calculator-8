package calculator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import calculator.parser.DelimiterParser;

public class StringCalculator {

    private final List<DelimiterParser> parsers;
    private final NumberConverter converter;
    private final NumberValidator validator;
    private final Adder adder;

    public StringCalculator(List<DelimiterParser> parsers,
            NumberConverter converter,
            NumberValidator validator,
            Adder adder) {
        this.parsers = parsers;
        this.converter = converter;
        this.validator = validator;
        this.adder = adder;
    }

    public BigInteger calculate(String input) {
        if (isNullOrEmpty(input)) {
            return BigInteger.ZERO;
        }

        String[] numberStrings = parse(input);
        BigInteger[] numbers = convertToNumbers(numberStrings);
        validateNumbers(numbers);
        return adder.sum(numbers);
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private String[] parse(String input) {
        return parsers.stream()
                .filter(parser -> parser.supports(input))
                .findFirst()
                .map(parser -> parser.parse(input))
                .orElseThrow(() -> new IllegalArgumentException("입력 형식이 올바르지 않습니다."));
    }

    private BigInteger[] convertToNumbers(String[] numberStrings) {
        return Arrays.stream(numberStrings)
                .map(converter::convert)
                .toArray(BigInteger[]::new);
    }

    private void validateNumbers(BigInteger[] numbers) {
        for (BigInteger number : numbers) {
            validator.validate(number);
        }
    }
}
