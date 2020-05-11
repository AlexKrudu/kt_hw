package expression.myExceptions;

public class ZeroDivisionException extends ParsingException {
    public ZeroDivisionException(String message) {
        super(message);
    }
}
