package expression;

import expression.myExceptions.ParsingException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Expression extends ToMiniString {
    int evaluate(int x) throws ParsingException;
}
