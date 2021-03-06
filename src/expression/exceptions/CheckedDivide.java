package expression.exceptions;

import expression.BinaryExpression;
import expression.CommonExpression;

import static java.lang.Math.round;

public class CheckedDivide extends BinaryExpression {
    public CheckedDivide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    /*
        @Override
        public String toMiniString() {
            Pair p = this.getMiniStrings();
            return p.getKey() + " / " + p.getValue();
        }
    */
    @Override
    public int evaluate(int x, int y) throws ParsingException {
        validate(x, y);
        return (x / y);
    }

    @Override
    public double evaluate(double x, double y) {
        return x / y;
    }

    @Override
    public void validate(int x, int y) throws ParsingException {
        if (y == 0) {
            throw new ZeroDivisionException("");
        }

        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowExceprion("");
        }
    }

}