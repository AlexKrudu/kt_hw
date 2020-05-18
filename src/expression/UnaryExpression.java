package expression;

import expression.exceptions.ParsingException;
import expression.operations.Operation;

public abstract class UnaryExpression<T> implements TripleExpressionGeneric<T> {
    private TripleExpressionGeneric<T> left;
    protected Operation<T> op;

    UnaryExpression(TripleExpressionGeneric<T> left, Operation<T> op) {
        this.left = left;
        this.op = op;
    }

    protected abstract T apply(T exp1) throws ParsingException;

    public T evaluate(T x, T y, T z) throws ParsingException {
        return this.apply(this.left.evaluate(x, y, z));
    }

}