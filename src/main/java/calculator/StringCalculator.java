package calculator;

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

    public long calculate(String input) {
        if (!validator.isValid(input) || input.isEmpty()) {
            return 0L;
        }

        String[] numberStrings = splitInput(input);
        long[] numbers = convertToNumbers(numberStrings);
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

    private long[] convertToNumbers(String[] numberStrings) {
        long[] numbers = new long[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = numberConverter.convert(numberStrings[i]);
        }
        return numbers;
    }

    private void validateNumbers(long[] numbers) {
        for (long number : numbers) {
            validator.validateNumber(number);
        }
    }
}
