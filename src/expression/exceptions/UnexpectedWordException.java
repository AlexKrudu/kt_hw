package expression.exceptions;

public class UnexpectedWordException extends ParsingException {
    public UnexpectedWordException(String word, int ind1, int ind2) {
        super("Unexpected word '" + word.substring(ind1, ind2) + "' at positions " + ind1 + "-" + ind2 + "\n" + word + "\n" + makePointer(ind1));
    }
}
