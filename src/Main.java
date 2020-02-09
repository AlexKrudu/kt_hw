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
        DoubleExpression exp1 = new Add(new Variable("x"), new Const(2.0));
        double x = exp1.evaluate(2.3);
        System.out.println(x);
    }
}
