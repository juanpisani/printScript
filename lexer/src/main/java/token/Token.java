package token;

public interface Token {

    String toString();
    TokenType getType();
    Object getLiteral();
    String getLexeme();
    int getLine();
}
