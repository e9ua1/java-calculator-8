package calculator;

import java.math.BigInteger;

public class InputValidator {

    public boolean isValid(String input) {
        return input != null;
    }

    public void validateNumber(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
        }
    }
}
