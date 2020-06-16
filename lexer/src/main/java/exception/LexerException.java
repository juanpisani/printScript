package exception;

public class LexerException extends Exception {

    private String message;
    private int line;

    public LexerException(String message, int line) {
        this.message = message;
        this.line = line;
    }

    @Override
    public String getMessage() {
        return message + " at line " + line;
    }

}
