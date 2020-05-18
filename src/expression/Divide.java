package expression;

import expression.operations.Operation;

public class Divide<T> extends BinaryExpressionGeneric<T> {
    public Divide(TripleExpressionGeneric<T> exp1, TripleExpressionGeneric<T> exp2, Operation<T> op) {
        super(exp1, exp2, op);
    }
    protected T apply(T exp1, T exp2){
        return this.op.div(exp1, exp2);
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
