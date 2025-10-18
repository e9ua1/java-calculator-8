package calculator;

public class StringCalculator {

    private final InputValidator validator;
    private final NumberConverter numberConverter;
    private final StringSplitter stringSplitter;
    private final Calculator calculator;

    public StringCalculator() {
        this.validator = new InputValidator();
        this.numberConverter = new NumberConverter();
        this.stringSplitter = new StringSplitter();
        this.calculator = new Calculator();
    }

    public long calculate(String input) {
        if (!validator.isValid(input) || input.isEmpty()) {
            return 0L;
        }

        if (!input.contains(",") && !input.contains(":")) {
            long number = numberConverter.convert(input);
            validator.validateNumber(number);
            return calculator.sum(new long[]{number});
        }

        String[] numberStrings = stringSplitter.split(input);
        long[] numbers = convertToNumbers(numberStrings);
        validateNumbers(numbers);
        return calculator.sum(numbers);
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
