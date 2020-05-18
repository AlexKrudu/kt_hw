package expression;

import expression.exceptions.ParsingException;
import expression.operations.Operation;

public class Negate<T> extends UnaryExpression<T> {
    public Negate(TripleExpressionGeneric<T> exp1, Operation<T> op) {
        super(exp1, op);
    }

    protected T apply(T exp1) throws ParsingException {
        return this.op.neg(exp1);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public int evaluate(int x) {
        return 0;
    }
}
