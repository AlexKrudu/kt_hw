package expression.parser.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import expression.myExceptions.*;

import static java.util.Map.entry;

public class Tokener {
    private List<Token> tokens = new ArrayList<>();
    private String expression;
    public int pos = -1;
    private int brackets = 0;
    private int value;
    private Set<String> potential_words = Set.of("x", "y", "z", "log2", "pow2");
    private Map<String, TokenType> operations = Map.ofEntries(
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
            switch (exp.charAt(i)) {
                case '+':
                    tokens.add(new Token(i, TokenType.ADD, "+"));
                    break;
                case '-':
                    tokens.add(new Token(i, TokenType.SUBTRACT, "-"));
                    break;
                case '*':
                    tokens.add(new Token(i, TokenType.MULTIPLY, "*"));
                    break;
                case '/':
                    tokens.add(new Token(i, TokenType.DIVIDE, "/"));
                    break;
                case '(':
                    brackets++;
                    tokens.add(new Token(i, TokenType.LBRACKET, "("));
                    break;
                case ')':
                    brackets--;
                    if (brackets < 0) {
                        throw new UnexpectedClosingParenthesisException(exp, i);
                    }
                    tokens.add(new Token(i, TokenType.RBRACKET, ")"));
                    break;
                case 'x':
                    tokens.add(new Token(i, TokenType.VARIABLE, "x"));
                    break;
                case 'y':
                    tokens.add(new Token(i, TokenType.VARIABLE, "y"));
                    break;
                case 'z':
                    tokens.add(new Token(i, TokenType.VARIABLE, "z"));
                    break;
                default:
                    int j = i;
                    if (Character.isLetterOrDigit(exp.charAt(j))){
                        while (Character.isLetterOrDigit(exp.charAt(j))){
                            j++;
                            if (j == exp.length()){
                                break;
                            }
                        }
                    }
                    if (potential_words.contains(exp.substring(i, j))){
                        if (exp.substring(i, j).equals("pow2")){
                            tokens.add(new Token(i, TokenType.POW2, exp.substring(i, j)));
                            i = j -1;
                            break;
                        }
                        else if (exp.substring(i, j).equals("log2")){
                            tokens.add(new Token(i, TokenType.LOG2, exp.substring(i, j)));
                            i = j - 1;
                            break;
                        }
                    }
                    if (Character.isDigit(exp.charAt(i))) {
                        tokens.add(new Token(i, TokenType.CONST, exp.substring(i, j)));
                        i = j - 1;
                    } else {
                        if (j - i > 1){
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
