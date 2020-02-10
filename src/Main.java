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
        Expression exp1 = new Add(new Variable("x"), new Const(2));
        Expression exp2 = x -> x+2;
        for (int i = 0; i < 10; i++) {
            int x1 = exp1.evaluate(i);
            int x2 = exp2.evaluate(i);
            System.out.println(x1);
            System.out.println(x2);
        }
    }
}
