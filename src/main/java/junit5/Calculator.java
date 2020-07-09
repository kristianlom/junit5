package junit5;

public class Calculator {

    public int add(int n1, int n2) {
        return n1 + n2;
    }

    public int subtract(int n1, int n2) {
        return n1 - n2;
    }

    public int divide(int n1, int n2) {
        return n1 / n2;
    }

    public int divideByZero(int n1, int n2) {
        if (n2 == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return n1 / n2;
    }

    public void longTaskOperation() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

    }
}
