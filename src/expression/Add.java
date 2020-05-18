package expression;

import expression.operations.Operation;

public class Add<T> extends BinaryExpression<T> {
    public Add(TripleExpressionGeneric<T> exp1, TripleExpressionGeneric<T> exp2, Operation<T> op) {
        super(exp1, exp2, op);
    }
    protected T apply(T exp1, T exp2){
        return this.op.add(exp1, exp2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }
}
