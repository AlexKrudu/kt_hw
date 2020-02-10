package expression;

public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {
    String toString();

    boolean equals(Object other);

    int getPriority();
}
