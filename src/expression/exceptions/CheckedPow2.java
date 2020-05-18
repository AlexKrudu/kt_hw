package expression.exceptions;

import expression.UnaryOperation;
import expression.CommonExpression;

public class CheckedPow2 extends UnaryOperation {
    public CheckedPow2(CommonExpression arg) {
        super(arg);
    }

    @Override
    public int evaluate(int x) throws ParsingException {
        validate(x);
        return 1 << x;
    }

    @Override
    protected void validate(int x) throws ParsingException {
        if (x > 31 || x < 0) {
            throw new OverflowExceprion("");
        }
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }
}