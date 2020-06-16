import exception.LexerException;
import interpreter.Interpreter;
import interpreter.InterpreterImplementation;
import lexer.Lexer;
import lexer.impl.LexerImplementation;
import parser.Parser;
import parser.impl.ParserImplementation;
import picocli.CommandLine;
import statement.Statement;
import token.Token;

import java.io.*;
import java.util.List;
import java.util.concurrent.Callable;

public class Tar implements Callable<Integer> {

    @CommandLine.Option(names = { "-f", "--file" }, paramLabel = "ARCHIVE", description = "the archive file")
    File archive;

    @CommandLine.Option(names = { "-m", "--mode" }, paramLabel = "MODE", description = "the mode of execution")
    String mode;

    @CommandLine.Option(names = { "-v", "--version" }, paramLabel = "VERSION", description = "version")
    Double version;

    private Lexer lexer;
    private Parser parser;
    private Interpreter interpreter;

    @Override
    public Integer call(){
        try {
            if (version != null){
                if (version != 1.0) {
                    throw new RuntimeException("Version not available");
                }
            }
            lexer = new LexerImplementation((new InputStreamReader(new FileInputStream(archive))));
            parser = new ParserImplementation(lexer.scanTokens());
            if (mode != null) {
                if (mode.equals("interpret")) {
                    interpreter = new InterpreterImplementation();
                    interpreter.interpret(parser.parse());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
