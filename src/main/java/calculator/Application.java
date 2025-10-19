package calculator;

import java.math.BigInteger;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        CalculatorFactory factory = new CalculatorFactory();
        StringCalculator calculator = factory.createCalculator();

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        BigInteger result = calculator.calculate(input);
        System.out.println("결과 : " + result);
    }
}
