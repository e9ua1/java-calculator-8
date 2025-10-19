package calculator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import calculator.parser.InputParser;

public class StringCalculator {

    private final InputParser parser;

    public StringCalculator(InputParser parser) {
        this.parser = parser;
    }

    public BigInteger calculate(String input) {
        if (isNullOrEmpty(input)) {
            return BigInteger.ZERO;
        }

        String[] numberStrings = parser.parse(input);
        Numbers numbers = convertToNumbers(numberStrings);
        return numbers.sum();
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private Numbers convertToNumbers(String[] numberStrings) {
        List<Number> numbers = Arrays.stream(numberStrings)
                .map(Number::new)
                .collect(Collectors.toList());
        return new Numbers(numbers);
    }
}
