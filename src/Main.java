import expression.*;
import expression.parser.*;

public class Main {
    private static Integer evaluate(final Expression expression, final int x) {
        try {
            return expression.evaluate(x);
        } catch (final ArithmeticException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        TripleExpression expression1 = new Subtract(new Const(3), new Const(2));
        TripleExpression expression2 = new Add(new Const(3), new Const(2));
        System.err.println(expression1.equals(expression2));
        TripleExpression exp = new ExpressionParser().parse("1 << 5 + 3");
        System.out.println(exp.evaluate(0, 1, 2));
    }
}
