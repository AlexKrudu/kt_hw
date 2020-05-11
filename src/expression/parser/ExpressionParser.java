package expression.parser;

import expression.*;
import expression.myExceptions.*;
import expression.parser.token.*;

import java.util.EnumSet;
import java.util.Set;

public class ExpressionParser implements Parser {
    private Tokener tokens;
    private static Set<TokenType> operations = EnumSet.of(TokenType.DIVIDE, TokenType.SUBTRACT, TokenType.MULTIPLY, TokenType.ADD, TokenType.POW2, TokenType.LOG2);
    private static Set<TokenType> operands = EnumSet.of(TokenType.VARIABLE, TokenType.CONST);

    @Override
    public TripleExpression parse(String expression) throws ParsingException {
        tokens = new Tokener(expression);
        return Expression();
    }


    private TripleExpression Expression() throws ParsingException {
        TripleExpression ter = Term();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case ADD:
                    ter = new CheckedAdd((CommonExpression) ter, (CommonExpression) Term());
                    break;
                case SUBTRACT:
                    ter = new CheckedSubtract((CommonExpression) ter, (CommonExpression) Term());
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

    private TripleExpression Term() throws ParsingException {
        TripleExpression prim = Factor();
        Token cur = tokens.getCur();
        loop:
        while (tokens.hasNext()) {
            switch (cur.getType()) {
                case MULTIPLY:
                    prim = new CheckedMultiply((CommonExpression) prim, (CommonExpression) Factor());
                    break;
                case DIVIDE:
                    prim = new CheckedDivide((CommonExpression) prim, (CommonExpression) Factor());
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

    private TripleExpression Factor() throws ParsingException {
        TripleExpression vc;
        Token token = tokens.getNext();
        switch (token.getType()) {
            case VARIABLE:
                vc = new Variable(token.value());
                tokens.getNext();
                break;
            case CONST:
                try {
                    Integer.parseInt(token.value());
                } catch (Exception e) {
                    throw new BadConstantException(tokens.getExpression(), token.getPos());
                }
                vc = new Const(Integer.parseInt(token.value()));
                tokens.getNext();
                break;
            case LBRACKET:
                vc = Expression();
                if (tokens.getCur().getType() != TokenType.RBRACKET) {
                    throw new MissingClosingParenthesisException(tokens.getExpression(), tokens.getCur().getPos());
                }
                tokens.getNext();
                break;
            case LOG2:
                vc = new CheckedLog2((CommonExpression) Factor());
                break;
            case POW2:
                vc = new CheckedPow2((CommonExpression) Factor());
                break;
            case SUBTRACT:
                if (tokens.getNext().getType() == TokenType.CONST) {
                    try {
                        Integer.parseInt('-' + tokens.getCur().value());
                    } catch (Exception e) {
                        throw new BadConstantException(tokens.getExpression(), token.getPos());
                    }
                    vc = new Const(Integer.parseInt('-' + tokens.getCur().value()));
                    tokens.getNext();
                    return vc;
                }
                tokens.getLast();
                if (tokens.getNext().getType() == TokenType.END) {
                    throw new UnexpectedOperationException(tokens.getExpression(), tokens.getLast().getPos());
                }
                tokens.getLast();
                vc = new CheckedNegate((CommonExpression) Factor());
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
