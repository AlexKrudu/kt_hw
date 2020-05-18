package expression.exceptions;

import expression.BinaryExpression;
import expression.CommonExpression;

public class CheckedAdd extends BinaryExpression {
    public CheckedAdd(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y) throws OverflowExceprion {
        validate(x, y);
        return x + y;
    }

    @Override
    public double evaluate(double x, double y) {
        return x + y;
    }

    @Override
    public void validate(int x, int y) throws OverflowExceprion {
        if ((x > 0 && Integer.MAX_VALUE - x < y) || (x < 0 && Integer.MIN_VALUE - x > y)) {
            throw new OverflowExceprion("");
        }
    }
}