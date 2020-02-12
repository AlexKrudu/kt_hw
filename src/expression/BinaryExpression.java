package expression;

public abstract class BinaryExpression implements CommonExpression {
    BinaryExpression(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            return this.left.equals(((BinaryExpression) other).left)
                    && this.right.equals(((BinaryExpression) other).right);
        }
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return evaluate(left.evaluate(x), right.evaluate(x));
    }


    @Override
    public double evaluate(double x) {
        return evaluate(left.evaluate(x), right.evaluate(x));
    }

    protected abstract int evaluate(int x, int y);

    protected abstract double evaluate(double x, double y);

    @Override
    public int hashCode() {
        return (2 * left.hashCode() + 2) * right.hashCode() + 8 * getClass().hashCode();
    }

    CommonExpression left;
    CommonExpression right;
}