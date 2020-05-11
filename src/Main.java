import expression.exceptions.ExpressionParser;
import expression.exceptions.ParsingException;

public class Main {
    public static void main(String[] args) throws ParsingException {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("log2 10").evaluate(0, 0, 0));
    }
}
