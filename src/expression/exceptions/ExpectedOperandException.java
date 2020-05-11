package expression.exceptions;

public class ExpectedOperandException extends ParsingException {
    public ExpectedOperandException(String word, int ind) {
        super("Expected operand at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
