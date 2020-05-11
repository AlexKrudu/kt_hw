package expression.myExceptions;

public class UnexpectedClosingParenthesisException extends ParsingException {
    public UnexpectedClosingParenthesisException(String word, int ind) {
        super("Unexpected closing parenthesis at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
