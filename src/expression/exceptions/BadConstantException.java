package expression.exceptions;

public class BadConstantException extends ParsingException {
    public BadConstantException(String word, int ind) {
        super("Bad constant at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
