package expression.operations;

import expression.exceptions.*;

public class ShortOperation implements Operation<Short> {
    public Short parseNumber(final String number) throws BadConstantException {
        return (short) Integer.parseInt(number);
    }

    public Short add(final Short x, final Short y) {
        return (short) (x + y);
    }

    public Short sub(final Short x, final Short y) {
        return (short) (x - y);
    }

    public Short mul(final Short x, final Short y) {
        return (short) (x * y);
    }

    private void checkZero(final Short y) throws BadOperationException {
        if (y == 0) {
            throw new BadOperationException("Division by zero");
        }
    }

    public Short div(final Short x, final Short y) throws BadOperationException {
        checkZero(y);
        return (short) (x / y);
    }

    public Short neg(final Short x) {
        return (short) (-x);
    }

}