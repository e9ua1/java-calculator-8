package calculator;

import java.math.BigInteger;

public class NumberConverter {

    public BigInteger convert(String numberStr) {
        try {
            return new BigInteger(numberStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다: " + numberStr);
        }
    }
}
