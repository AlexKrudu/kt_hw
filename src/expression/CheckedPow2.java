package expression;


import expression.myExceptions.OverflowExceprion;
import expression.myExceptions.ParsingException;

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
    public int getPriority() {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }
}