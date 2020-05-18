package expression;

import expression.exceptions.ParsingException;
import expression.operations.Operation;

public abstract class BinaryExpressionGeneric<T> implements TripleExpressionGeneric<T> {
    private TripleExpressionGeneric<T> left;
    private TripleExpressionGeneric<T> right;
    protected Operation<T> op;

    BinaryExpressionGeneric(TripleExpressionGeneric<T> left, TripleExpressionGeneric<T> right, Operation<T> op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    protected abstract T apply(T exp1, T exp2) throws ParsingException;

    public T evaluate(T x, T y, T z) throws ParsingException {
        return this.apply(this.left.evaluate(x, y, z), this.right.evaluate(x, y, z));
    }

}