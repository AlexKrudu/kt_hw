package expression;

public class Const<T> implements TripleExpressionGeneric<T> {
    private T value;

    public Const(T x) {
        value = x;
    }

    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }
}