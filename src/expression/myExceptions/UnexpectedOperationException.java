package expression.myExceptions;

public class UnexpectedOperationException extends ParsingException {
    public UnexpectedOperationException(String word, int ind) {
        super("Unexpected operation '" + word.charAt(ind) + "' at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
