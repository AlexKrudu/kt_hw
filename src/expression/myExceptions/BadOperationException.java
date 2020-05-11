package expression.myExceptions;

public class BadOperationException extends ParsingException {
    public BadOperationException(String message) {
        super(message);
    }
}
