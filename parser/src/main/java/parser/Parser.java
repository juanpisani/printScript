package parser;

import expression.Expression;
import statement.Statement;
import token.Token;

import java.util.List;

public interface Parser {

    List<Statement> parse();
}
