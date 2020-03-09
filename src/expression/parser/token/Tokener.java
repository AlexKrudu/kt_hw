package expression.parser.token;

import java.util.ArrayList;
import java.util.List;

public class Tokener {
    private List<Token> tokens = new ArrayList<>();
    private String expression;
    public int pos = -1;

    public Tokener(String expression) {
        this.expression = expression;
        MakeTokens(expression);

    }

    private void MakeTokens(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            if (Character.isWhitespace(exp.charAt(i))) {
                continue;
            }
            switch (exp.charAt(i)) {
                case '>':
                    if (exp.charAt(i+1) == '>'){
                        tokens.add(new Token(i, TokenType.RSHIFT, ">>"));
                    }
                    break;
                case '<':
                    if (exp.charAt(i+1) == '<'){
                        tokens.add(new Token(i, TokenType.LSHIFT, "<<"));
                    }
                    break;
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
                    tokens.add(new Token(i, TokenType.LBRACKET, "("));
                    break;
                case ')':
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
                    while (Character.isDigit(exp.charAt(j))) {
                        j++;
                        if (j == exp.length()) {
                            break;
                        }
                    }
                    tokens.add(new Token(i, TokenType.CONST, exp.substring(i, j)));
                    i = j - 1;

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
