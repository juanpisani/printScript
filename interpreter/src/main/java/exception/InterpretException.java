package exception;

import token.Token;

public class InterpretException extends RuntimeException {
    private Token operator;
    private String message;

    public InterpretException(Token operator, String message) {
        this.operator = operator;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + " at line " + operator.getLine();
    }
}
