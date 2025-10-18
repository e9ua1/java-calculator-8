package calculator;

public class NumberConverter {

    public long convert(String numberStr) {
        try {
            return Long.parseLong(numberStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다: " + numberStr);
        }
    }
}
