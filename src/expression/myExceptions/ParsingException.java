package expression.myExceptions;

public class ParsingException extends Exception {
    public ParsingException(String message) {
        super(message);
    }

    static protected String makePointer(int ind) {
        return "-".repeat(Math.max(0, ind)) +
                "^\n";
    }
}
