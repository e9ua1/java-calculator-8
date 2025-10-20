package calculator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {

    private final List<Number> values;

    public Numbers(List<Number> numbers) {
        this.values = new ArrayList<>(numbers);
    }

    public static Numbers from(String[] numberStrings) {
        List<Number> numbers = Arrays.stream(numberStrings)
                .map(Number::new)
                .collect(Collectors.toList());
        return new Numbers(numbers);
    }

    public BigInteger sum() {
        return values.stream()
                .map(Number::getValue)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
