package calculator;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void 빈_배열은_0을_반환한다() {
        Adder calculator = new Adder();
        BigInteger result = calculator.sum(new BigInteger[]{});
        assertThat(result).isEqualTo(BigInteger.ZERO);
    }

    @Test
    void 정수_배열의_합을_계산한다() {
        Adder calculator = new Adder();
        BigInteger result = calculator.sum(new BigInteger[]{
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        });
        assertThat(result).isEqualTo(6L);
    }

    @Test
    void 큰_숫자의_합을_계산한다() {
        Adder calculator = new Adder();
        BigInteger result = calculator.sum(new BigInteger[]{
                new BigInteger("999999999999999999"),
                new BigInteger("999999999999999999")
        });
        assertThat(result).isEqualTo(new BigInteger("1999999999999999998"));
    }
}
