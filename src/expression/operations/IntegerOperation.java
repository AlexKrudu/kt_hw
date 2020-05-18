package expression.operations;

import expression.exceptions.*;

public class IntegerOperation implements Operation<Integer> {

    public Integer parseNumber(final String number) throws BadConstantException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new BadConstantException("", -1);
        }
    }

    private void checkAdd(final Integer x, final Integer y) throws OverflowExceprion {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowExceprion("");
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowExceprion("");
        }
    }

    public Integer add(final Integer x, final Integer y) throws OverflowExceprion {
        checkAdd(x, y);
        return x + y;
    }

    private void checkSub(final Integer x, final Integer y) throws OverflowExceprion {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new OverflowExceprion("");
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new OverflowExceprion("");
        }
    }

    public Integer sub(final Integer x, final Integer y) throws OverflowExceprion {
        checkSub(x, y);
        return x - y;
    }

    private void checkMul(final Integer x, final Integer y) throws OverflowExceprion {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowExceprion("");
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowExceprion("");
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowExceprion("");
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowExceprion("");
        }
    }

    public Integer mul(final Integer x, final Integer y) throws OverflowExceprion {
        checkMul(x, y);

        return x * y;
    }

    private void checkDiv(final Integer x, final Integer y) throws OverflowExceprion {
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowExceprion("");
        }
    }

    private void checkZero(final int y) throws BadOperationException {
        if (y == 0) {
            throw new BadOperationException("Division by zero");
        }
    }

    public Integer div(final Integer x, final Integer y) throws BadOperationException, OverflowExceprion {
        checkZero(y);
        checkDiv(x, y);
        return x / y;
    }

    private void checkNeg(final Integer x) throws OverflowExceprion {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowExceprion("");
        }
    }

    public Integer neg(final Integer x) throws OverflowExceprion {
        checkNeg(x);
        return -x;
    }

}