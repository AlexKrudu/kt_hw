package expression;

public class Subtract extends BinaryExpression {
    public Subtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return '(' + left.toString() + " - " + right.toString() + ')';
    }

    /*
        @Override
        public String toMiniString() {
            Pair p = this.getMiniStrings();
            return p.getKey() + " - " + p.getValue();
        }
    */
    @Override
    public int evaluate(int x, int y) {
        return x - y;
    }

    @Override
    public double evaluate(double x, double y) {
        return x - y;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
