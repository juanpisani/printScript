package exception;

import token.Token;

public class ParseException extends RuntimeException{

    private String message;
    private Token token;

    public ParseException(String message, Token token) {
        this.message = message;
        this.token = token;
    }

    @Override
    public String getMessage() {
        return message + " at line " + token.getLine();
    }
}
