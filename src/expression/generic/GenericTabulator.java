package expression.generic;

import expression.TripleExpressionGeneric;
import expression.exceptions.*;
import expression.operations.*;
import expression.parser.ExpressionParser;

import java.util.HashMap;

public class GenericTabulator implements Tabulator {
    private final static HashMap<String, Operation<? extends Object>> MODES = new HashMap<>();

    static {
        MODES.put("i", new IntegerOperation());
        MODES.put("bi", new BigIntegerOperation());
        MODES.put("d", new DoubleOperation());
        MODES.put("l", new LongOperation());
        MODES.put("s", new ShortOperation());
    }

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        return makeTable(getOperation(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private Operation<?> getOperation(final String mode) {
        return MODES.get(mode);
    }

    private <T> Object[][][] makeTable(Operation<T> op, String expression, int x1, int x2, int y1, int y2, int z1,
                                       int z2) {
        Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser<T> parser = new ExpressionParser<>(op);
        TripleExpressionGeneric<T> exp;
        try {
            exp = parser.parse(expression);
        } catch (ParsingException e) {
            return res;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        res[i - x1][j - y1][k - z1] = exp.evaluate(op.parseNumber(Integer.toString(i)),
                                op.parseNumber(Integer.toString(j)), op.parseNumber(Integer.toString(k)));
                    } catch (ParsingException e) {
                        res[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return res;
    }
}