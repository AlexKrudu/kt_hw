package expression.exceptions;

import expression.BinaryExpression;
import expression.CommonExpression;


public class CheckedSubtract extends BinaryExpression {
    public CheckedSubtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    /*
        @Override
        public String toMiniString() {
            Pair p = this.getMiniStrings();
            return p.getKey() + " - " + p.getValue();
        }
    */
    @Override
    public int evaluate(int x, int y) throws OverflowExceprion {
        validate(x, y);
        return x - y;
    }

    @Override
    public double evaluate(double x, double y) {
        return x - y;
    }

    @Override
    public void validate(int x, int y) throws OverflowExceprion {
        if ((x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) || (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y)) {
            throw new OverflowExceprion("");
        }
    }

}