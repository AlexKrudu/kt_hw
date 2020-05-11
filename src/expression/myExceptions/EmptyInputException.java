package expression.myExceptions;

public class EmptyInputException extends ParsingException {
    public EmptyInputException(String message) {
        super(message);
    }
}
