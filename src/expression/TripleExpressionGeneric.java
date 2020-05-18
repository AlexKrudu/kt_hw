package expression;

import expression.exceptions.ParsingException;

public interface TripleExpressionGeneric<T> extends ToMiniString, TripleExpression, CommonExpression {
    T evaluate(T x, T y, T z) throws ParsingException;
}