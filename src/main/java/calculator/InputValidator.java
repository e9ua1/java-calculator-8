package calculator;

public class InputValidator {

    public boolean isValid(String input) {
        return input != null;
    }

    public void validateNumber(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
        }
    }
}
