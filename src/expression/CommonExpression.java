package expression;

public interface CommonExpression extends Expression, DoubleExpression {
    String toString();

    boolean equals(Object other);

    int getPriority();
}
