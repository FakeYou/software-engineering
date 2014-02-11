package multiformat;

/**
 * Created by FakeYou on 2/11/14.
 */
public class DivideByZeroException extends ArithmeticException  {
    public DivideByZeroException() {
        super("Cannot divide by zero");
    }
}
