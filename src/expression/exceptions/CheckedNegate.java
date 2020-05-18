package expression.exceptions;

import expression.UnaryOperation;
import expression.CommonExpression;

public class CheckedNegate extends UnaryOperation {
    public CheckedNegate(CommonExpression exp) {
        super(exp);
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