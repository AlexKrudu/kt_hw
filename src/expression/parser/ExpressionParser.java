package expression.parser;

import expression.Add;
import expression.Const;
import expression.TripleExpression;
import expression.Variable;
import expression.parser.token.*;

public class ExpressionParser {
    private String expression;
    private Tokener tokens;
    private int pos;

    public TripleExpression parse(String expression) {
        tokens = new Tokener(expression);
        return Expression();

    }

    private TripleExpression Expression() {
        TripleExpression res = Term();
        return res;
    }

    private TripleExpression Term() {
        TripleExpression fac = Factor();
        return fac;
    }

    private TripleExpression Factor() {
        TripleExpression prim = VarsAndConst();
        return prim;
    }

    private TripleExpression VarsAndConst() {
        TripleExpression vc;
        Token token = tokens.getNext();
        switch (token.getType()) {
            case VARIABLE:
                vc = new Variable(token.value());
                break;
            case CONST:
                vc = new Const(Integer.parseInt(token.value()));
                break;
            case LBRACKET:
                vc = Expression();
            default:
                vc = new Const(2);
        }
        return vc;
    }
}
