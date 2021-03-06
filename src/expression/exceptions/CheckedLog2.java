package expression.exceptions;

import expression.UnaryOperation;
import expression.CommonExpression;

public class CheckedLog2 extends UnaryOperation {
    public CheckedLog2(CommonExpression exp) {
        super(exp);
    }

    @Override
    protected void validate(int x) throws OverflowExceprion, ParsingException {
        if (x <= 0) {
            throw new BadOperationException("Log2 from negative or zero");
        }
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public int evaluate(int x) throws ParsingException {
        validate(x);
        int result = 0;
        while (x != 0 && x != 1) {
            result++;
            x /= 2;
        }
        return result;
    }
}