package calculator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private final List<Number> values;

    public Numbers(List<Number> numbers) {
        this.values = new ArrayList<>(numbers);
    }

    public BigInteger sum() {
        return values.stream()
                .map(Number::getValue)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
