package calculator;

import java.math.BigInteger;

public class StringCalculator {

    private final InputValidator validator;
    private final NumberConverter numberConverter;
    private final DelimiterExtractor delimiterExtractor;
    private final StringSplitter stringSplitter;
    private final Calculator calculator;

    public StringCalculator() {
        this.validator = new InputValidator();
        this.numberConverter = new NumberConverter();
        this.delimiterExtractor = new DelimiterExtractor();
        this.stringSplitter = new StringSplitter();
        this.calculator = new Calculator();
    }

    public BigInteger calculate(String input) {
        if (!validator.isValid(input) || input.isEmpty()) {
            return BigInteger.ZERO;
        }

        String[] numberStrings = splitInput(input);
        BigInteger[] numbers = convertToNumbers(numberStrings);
        validateNumbers(numbers);
        return calculator.sum(numbers);
    }

    private String[] splitInput(String input) {
        if (delimiterExtractor.hasCustomDelimiter(input)) {
            String delimiter = delimiterExtractor.extractCustomDelimiter(input);
            String numbersStrings = delimiterExtractor.extractNumbers(input);
            return stringSplitter.split(numbersStrings, delimiter);
        }

        if (!input.contains(",") && !input.contains(":")) {
            return new String[]{input};
        }

        return stringSplitter.split(input);
    }

    private BigInteger[] convertToNumbers(String[] numberStrings) {
        BigInteger[] numbers = new BigInteger[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = numberConverter.convert(numberStrings[i]);
        }
        return numbers;
    }

    private void validateNumbers(BigInteger[] numbers) {
        for (BigInteger number : numbers) {
            validator.validateNumber(number);
        }
    }
}
