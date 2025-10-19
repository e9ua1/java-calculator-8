package calculator;

import java.math.BigInteger;

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
        Numbers numbers = new Numbers(numberStrings);
        return numbers.sum();
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
