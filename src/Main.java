import expression.*;

public class Main {
    private static Integer evaluate(final Expression expression, final int x) {
        try {
            return expression.evaluate(x);
        } catch (final ArithmeticException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        TripleExpression exp1 = new Add(new Variable("x"), new Variable("y"));
        System.out.println(exp1.evaluate(1,2,1));
    }
}
