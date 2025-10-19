package calculator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    private final List<Number> values;

    public Numbers(String[] numberStrings) {
        this.values = Arrays.stream(numberStrings)
                .map(Number::new)
                .collect(Collectors.toList());
    }

    public BigInteger sum() {
        return values.stream()
                .map(Number::getValue)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
