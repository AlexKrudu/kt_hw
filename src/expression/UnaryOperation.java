package expression;

public abstract class UnaryOperation implements CommonExpression {
    UnaryOperation(CommonExpression exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().equals(other.getClass())) {
            return this.exp.equals(((UnaryOperation) other).exp);
        }
        return false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(exp.evaluate(x, y, z));
    }


    @Override
    public int hashCode() {
        return (2 * exp.hashCode() + 2) + 8 * getClass().hashCode();
    }

    CommonExpression exp;
}