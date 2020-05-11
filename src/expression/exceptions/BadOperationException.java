package expression.exceptions;

public class BadOperationException extends ParsingException {
    public BadOperationException(String message) {
        super(message);
    }
}
