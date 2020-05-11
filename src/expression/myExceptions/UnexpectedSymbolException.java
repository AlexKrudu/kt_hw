package expression.myExceptions;

public class UnexpectedSymbolException extends ParsingException {
    public UnexpectedSymbolException(String word, int ind) {
        super("Unexpected symbol '" + word.charAt(ind) + "' at position " + ind + "\n" + word + "\n" + makePointer(ind));
    }
}
