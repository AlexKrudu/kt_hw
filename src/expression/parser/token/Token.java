package expression.parser.token;

public class Token {
    private int pos;
    private TokenType type;
    private String value;

    public Token(int pos, TokenType type, String value) {
        this.pos = pos;
        this.type = type;
        this.value = value;
    }

    public int getPos() {
        return this.pos;
    }

    public TokenType getType() {
        return this.type;
    }

    public String value() {
        return this.value;
    }
}
