package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.operations.Operation;
import expression.parser.token.*;

import java.util.EnumSet;
import java.util.Set;

public class ExpressionParser<T> implements Parser {
    private Tokener<T> tokens;
    private static Set<TokenType> operations = EnumSet.of(TokenType.DIVIDE, TokenType.SUBTRACT, TokenType.MULTIPLY, TokenType.ADD, TokenType.POW2, TokenType.LOG2);
    private static Set<TokenType> operands = EnumSet.of(TokenType.VARIABLE, TokenType.CONST);
    private Operation<T> op;

    public ExpressionParser(Operation<T> op) {
        this.op = op;
    }

    public ExpressionParser() {
        this.op = null;
    }


    @Override
    public TripleExpressionGeneric<T> parse(String expression) throws ParsingException {
        tokens = new Tokener<T>(expression);
        return Expression();
    }


    private TripleExpressionGeneric<T> Expression() throws ParsingException {
        TripleExpressionGeneric<T> ter = Term();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case ADD:
                    ter = new Add<T>(ter, Term(), op);
                    break;
                case SUBTRACT:
                    ter = new Subtract<T>(ter, Term(), op);
                    break;
                case RBRACKET:
                    break loop;
                default:
                    throw new ParsingException("Invalid expression!" + "\n" + tokens.getExpression());
            }
            cur = tokens.getCur();
        }
        return ter;
    }

    private TripleExpressionGeneric<T> Term() throws ParsingException {
        TripleExpressionGeneric<T> prim = Factor();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case MULTIPLY:
                    prim = new Multiply<T>(prim, Factor(), op);
                    break;
                case DIVIDE:
                    prim = new Divide<T>(prim, Factor(), op);
                    break;
                case RBRACKET:
                case ADD:
                case SUBTRACT:
                    break loop;
                default:
                    if (operands.contains(cur.getType())) {
                        throw new UnexpectedOperandException(tokens.getExpression(), cur.getPos());
                    }
                    throw new ParsingException("Invalid expression!" + "\n" + tokens.getExpression());
            }
            cur = tokens.getCur();
        }
        return prim;
    }

    private TripleExpressionGeneric<T> Factor() throws ParsingException {
        TripleExpressionGeneric<T> vc;
        Token token = tokens.getNext();
        switch (token.getType()) {
            case VARIABLE:
                vc = new Variable<T>(token.value());
                tokens.getNext();
                break;
            case CONST:
                try {
                    Integer.parseInt(token.value());
                } catch (Exception e) {
                    throw new BadConstantException(tokens.getExpression(), token.getPos());
                }
                vc = new Const<T>(op.parseNumber(token.value()));
                tokens.getNext();
                break;
            case LBRACKET:
                vc = Expression();
                if (tokens.getCur().getType() != TokenType.RBRACKET) {
                    throw new MissingClosingParenthesisException(tokens.getExpression(), tokens.getCur().getPos());
                }
                tokens.getNext();
                break;
            case SUBTRACT:
                if (tokens.getNext().getType() == TokenType.CONST) {
                    try {
                        Integer.parseInt('-' + tokens.getCur().value());
                    } catch (Exception e) {
                        throw new BadConstantException(tokens.getExpression(), token.getPos());
                    }
                    vc = new Const<T>(op.parseNumber('-' + tokens.getCur().value()));
                    tokens.getNext();
                    return vc;
                }
                tokens.getLast();
                if (tokens.getNext().getType() == TokenType.END) {
                    throw new UnexpectedOperationException(tokens.getExpression(), tokens.getLast().getPos());
                }
                tokens.getLast();
                vc = new Negate<T>(Factor(), op);
                break;
            case END:
            case RBRACKET:
                try {
                    if (operations.contains(tokens.getLast().getType())) {
                        throw new ExpectedOperandException(tokens.getExpression(), tokens.getNext().getPos());
                    } else if (tokens.getCur().getType() == TokenType.LBRACKET) {
                        throw new EmptyInputException("Got empty expression in brackets! " + tokens.getExpression());
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new EmptyInputException("Got empty input!" + tokens.getExpression());
                }

            default:
                if (operations.contains(token.getType())) {
                    throw new UnexpectedOperationException(tokens.getExpression(), token.getPos());
                }
                throw new ParsingException("Invalid expression!" + "\n" + tokens.getExpression());
        }

        return vc;
    }

}
