package expression.parser.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import expression.myExceptions.*;

import static java.util.Map.entry;

public class Tokener {
    private List<Token> tokens = new ArrayList<>();
    private String expression;
    public int pos = -1;
    private int brackets = 0;
    private Map<String, TokenType> toToken = Map.ofEntries(
            entry("+", TokenType.ADD),
            entry("-", TokenType.SUBTRACT),
            entry("*", TokenType.MULTIPLY),
            entry("/", TokenType.DIVIDE),
            entry("x", TokenType.VARIABLE),
            entry("y", TokenType.VARIABLE),
            entry("z", TokenType.VARIABLE),
            entry("(", TokenType.LBRACKET),
            entry(")", TokenType.RBRACKET),
            entry("log2", TokenType.LOG2),
            entry("pow2", TokenType.POW2)
    );

    public Tokener(String expression) throws ParsingException {
        this.expression = expression;
        MakeTokens(expression);

    }

    public String getExpression() {
        return expression;
    }

    private void MakeTokens(String exp) throws ParsingException {
        for (int i = 0; i < exp.length(); i++) {
            if (Character.isWhitespace(exp.charAt(i))) {
                continue;
            }
            if (toToken.containsKey(String.valueOf(exp.charAt(i)))) {
                tokens.add(new Token(i, toToken.get(String.valueOf(exp.charAt(i))), String.valueOf(exp.charAt(i))));
                if ("(".equals(String.valueOf(exp.charAt(i)))) {
                    brackets++;
                } else if (")".equals(String.valueOf(exp.charAt(i)))) {
                    brackets--;
                    if (brackets < 0) {
                        throw new UnexpectedClosingParenthesisException(exp, i);
                    }
                }
            } else {
                int j = i;
                if (Character.isLetterOrDigit(exp.charAt(j))) {
                    while (Character.isLetterOrDigit(exp.charAt(j))) {
                        j++;
                        if (j == exp.length()) {
                            break;
                        }
                    }
                }
                if (toToken.containsKey(exp.substring(i, j))) {
                    tokens.add(new Token(i, toToken.get(exp.substring(i, j)), exp.substring(i, j)));
                    i = j - 1;
                    continue;
                }
                if (Character.isDigit(exp.charAt(i))) {
                    tokens.add(new Token(i, TokenType.CONST, exp.substring(i, j)));
                    i = j - 1;
                } else {
                    if (j - i > 1) {
                        throw new UnexpectedWordException(exp, i, j);
                    }
                    throw new UnexpectedSymbolException(exp, i);
                }
            }
        }
        tokens.add(new Token(exp.length(), TokenType.END, "end"));
    }

    public Token getNext() {
        return tokens.get(++pos);
    }

    public Token getCur() {
        return tokens.get(pos);
    }

    public Token getLast() {
        return tokens.get(--pos);
    }

    public boolean hasNext() {
        return pos + 1 < tokens.size();
    }
}
