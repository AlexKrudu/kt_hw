package expression.operations;

import expression.exceptions.*;

public interface Operation<T> {
    T parseNumber(String number) throws BadConstantException;

    T add(T exp1, T exp2) throws OverflowExceprion;

    T sub(T exp1, T exp2) throws OverflowExceprion;

    T mul(T exp1, T exp2) throws OverflowExceprion;

    T div(T exp1, T exp2) throws ParsingException;

    T neg(T exp1) throws OverflowExceprion;

}
