package expression.exceptions;

public class UnexpectedOperandException extends ParsingException {
    public UnexpectedOperandException(String word, int ind) {
        super("Unexpected operand at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
