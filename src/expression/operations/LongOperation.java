package expression.operations;

import expression.exceptions.*;

public class LongOperation implements Operation<Long> {
    public Long parseNumber(final String number) throws BadConstantException {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException NFM) {
            throw new BadConstantException("", -1);
        }
    }

    public Long add(final Long x, final Long y) {
        return x + y;
    }

    public Long sub(final Long x, final Long y) {
        return x - y;
    }

    public Long mul(final Long x, final Long y) {
        return x * y;
    }

    private void checkZero(final Long y) throws BadOperationException {
        if (y == 0) {
            throw new BadOperationException("Division by zero");
        }
    }

    public Long div(final Long x, final Long y) throws BadOperationException {
        checkZero(y);
        return x / y;
    }

    public Long neg(final Long x) {
        return -x;
    }

}