package environment;

import token.TokenType;

public class DeclarationImplementation implements Declaration{

    private TokenType keyword;
    private TokenType type;
    private Object value;

    public DeclarationImplementation(TokenType keyword, TokenType type, Object value) {
        this.keyword = keyword;
        this.type = type;
        this.value = value;
    }

    public TokenType getKeyword() {
        return keyword;
    }

    public TokenType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
