package calculator;

import java.math.BigInteger;

public class Calculator {

    public BigInteger sum(BigInteger[] numbers) {
        BigInteger total = BigInteger.ZERO;
        for (BigInteger number : numbers) {
            total = total.add(number);
        }
        return total;
    }
}
