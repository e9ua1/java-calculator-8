package calculator;

import java.math.BigInteger;

public class Number {

    private final BigInteger value;

    public Number(String numberStr) {
        this.value = parseAndValidate(numberStr);
    }

    private BigInteger parseAndValidate(String numberStr) {
        BigInteger parsed = parse(numberStr);
        validatePositive(parsed);
        return parsed;
    }

    private BigInteger parse(String numberStr) {
        try {
            return new BigInteger(numberStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다: " + numberStr);
        }
    }

    private void validatePositive(BigInteger value) {
        if (value.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + value);
        }
    }

    public BigInteger getValue() {
        return value;
    }
}
