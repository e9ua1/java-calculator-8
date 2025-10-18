package calculator;

public class StringCalculator {

    private final InputValidator validator;

    public StringCalculator() {
        this.validator = new InputValidator();
    }

    public long calculate(String input) {
        if (!validator.isValid(input) || input.isEmpty()) {
            return 0L;
        }
        return 0L;
    }
}
