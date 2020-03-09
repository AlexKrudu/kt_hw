package expression.parser;

import expression.*;
import expression.parser.token.*;

public class ExpressionParser {
    private String expression;
    private Tokener tokens;
    private int pos;

    public TripleExpression parse(String expression) {
        tokens = new Tokener(expression);
        return Shifts();
    }

    private TripleExpression Shifts() {
        TripleExpression res = Expression();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case LSHIFT:
                    res = new LeftShift((CommonExpression) res, (CommonExpression) Expression());
                    break;
                case RSHIFT:
                    res = new RightShift((CommonExpression) res, (CommonExpression) Expression());
                    break;
                case RBRACKET:
                    break loop;
                default:
                    throw new RuntimeException("govno");
            }
            cur = tokens.getCur();
        }
        return res;
    }

    private TripleExpression Expression() {
        TripleExpression ter = Term();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case ADD:
                    ter = new Add((CommonExpression) ter, (CommonExpression) Term());
                    break;
                case SUBTRACT:
                    ter = new Subtract((CommonExpression) ter, (CommonExpression) Term());
                    break;
                case RBRACKET:
                case LSHIFT:
                case RSHIFT:
                    break loop;
                default:
                    throw new RuntimeException("govno");
            }
            cur = tokens.getCur();
        }
        return ter;
    }

    private TripleExpression Term() {
        TripleExpression prim = Factor();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case MULTIPLY:
                    prim = new Multiply((CommonExpression) prim, (CommonExpression) Factor());
                    break;
                case DIVIDE:
                    prim = new Divide((CommonExpression) prim, (CommonExpression) Factor());
                    break;
                case RBRACKET:
                case ADD:
                case SUBTRACT:
                case RSHIFT:
                case LSHIFT:
                    break loop;
                default:
                    throw new RuntimeException("govno");
            }
            cur = tokens.getCur();
        }
        return prim;
    }

    private TripleExpression Factor() {
        TripleExpression vc;
        Token token = tokens.getNext();
        switch (token.getType()) {
            case VARIABLE:
                vc = new Variable(token.value());
                tokens.getNext();
                break;
            case CONST:
                vc = new Const(Integer.parseInt(token.value()));
                tokens.getNext();
                break;
            case LBRACKET:
                vc = Shifts();
                tokens.getNext();
                break;
            case SUBTRACT:
                if (tokens.getNext().getType() == TokenType.CONST) {
                    vc = new Const(Integer.parseInt('-' + tokens.getCur().value()));
                    tokens.getNext();
                    return vc;
                }
                tokens.getLast();
                vc = new Negate((CommonExpression) Factor());
                break;
            default:
                throw new RuntimeException("govno");
        }

        return vc;
    }
}
