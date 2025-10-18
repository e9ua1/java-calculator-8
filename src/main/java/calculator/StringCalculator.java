package calculator;

public class StringCalculator {

    private final InputValidator validator;
    private final NumberConverter numberConverter;
    private final Calculator calculator;

    public StringCalculator() {
        this.validator = new InputValidator();
        this.numberConverter = new NumberConverter();
        this.calculator = new Calculator();
    }

    public long calculate(String input) {
        if (!validator.isValid(input) || input.isEmpty()) {
            return 0L;
        }

        long number = numberConverter.convert(input);
        validator.validateNumber(number);
        return calculator.sum(new long[]{number});
    }
}
