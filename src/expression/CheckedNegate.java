package expression;

import expression.myExceptions.OverflowExceprion;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(CommonExpression exp) {
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
    public int evaluate(int x) throws OverflowExceprion {
        validate(x);
        return -x;
    }

    @Override
    public void validate(int x) throws OverflowExceprion {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowExceprion("");
        }
    }
}