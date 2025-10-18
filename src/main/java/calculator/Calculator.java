package calculator;

public class Calculator {

    public long sum(long[] numbers) {
        long total = 0L;
        for (long number : numbers) {
            total += number;
        }
        return total;
    }
}
