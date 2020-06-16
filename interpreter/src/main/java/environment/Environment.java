package environment;

import token.Token;
import token.TokenType;

public interface Environment {
    Object get(Token name);
    void add(String name, TokenType keyword, TokenType type, Object value);
    Environment getEnclosing();
    void assign(Token name, Object value);
}
