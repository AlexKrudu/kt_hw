package expression;

public class Variable<T> implements TripleExpressionGeneric<T> {
    private String name;

    public Variable(String x) {
        name = x;
    }

    public T evaluate(T x, T y, T z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return 0;
    }
}