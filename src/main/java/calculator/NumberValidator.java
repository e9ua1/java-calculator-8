package calculator;

import java.math.BigInteger;

public class NumberValidator {

    public void validate(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
        }
    }
}
