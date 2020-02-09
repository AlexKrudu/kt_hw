package expression;

public abstract class BinaryExpression implements CommonExpression {
    BinaryExpression(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null){
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            return this.left.equals(((BinaryExpression) other).left)
                    && this.right.equals(((BinaryExpression) other).right);
        }
        return false;
    }
    @Override
    public int evaluate(int x){
        return evaluate(left.evaluate(x),right.evaluate(x));
    };
    @Override
    public double evaluate(double x){
        return evaluate(left.evaluate(x), right.evaluate(x);
    }
    protected abstract int evaluate(int x, int y);
    protected abstract double evaluate(double x, double y);
    @Override
    public int hashCode() {
        return (13 * left.hashCode() + 17) * right.hashCode() + 19 * getClass().hashCode();
    }

    Expression left;
    Expression right;
}