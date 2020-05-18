package expression.operations;

import expression.exceptions.*;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {
    public BigInteger parseNumber(final String number) throws BadConstantException {
        try {
            return new BigInteger(number);
        } catch (NumberFormatException NFE) {
            throw new BadConstantException("", -1);
        }
    }

    public BigInteger add(final BigInteger x, final BigInteger y) {
        return x.add(y);
    }

    public BigInteger sub(final BigInteger x, final BigInteger y) {
        return x.subtract(y);
    }

    public BigInteger mul(final BigInteger x, final BigInteger y) {
        return x.multiply(y);
    }

    private void checkZero(final BigInteger y) throws BadOperationException {
        if (y.equals(BigInteger.ZERO)) {
            throw new BadOperationException("Division by zero");
        }
    }

    public BigInteger div(final BigInteger x, final BigInteger y) throws BadOperationException {
        checkZero(y);
        return x.divide(y);
    }

    public BigInteger neg(final BigInteger x) {
        return x.negate();
    }

}