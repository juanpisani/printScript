package lexer;

import exception.LexerException;
import token.Token;

import java.util.List;

public interface Lexer {

    List<Token> scanTokens() throws LexerException;

}
