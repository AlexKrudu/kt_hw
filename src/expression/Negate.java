package expression;

public class Negate extends UnaryOperation {
    public Negate(CommonExpression exp) {
        super(exp);
    }

    @Override
    public String toString() {
        return "-(" + exp.toString() + ')';
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return -x;
    }

    @Override
    public int evaluate(int x) {
        return -x;
    }
}
