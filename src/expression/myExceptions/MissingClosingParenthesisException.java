package expression.myExceptions;

public class MissingClosingParenthesisException extends ParsingException {
    public MissingClosingParenthesisException(String word, int ind) {
        super("Missing closing parenthesis at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
