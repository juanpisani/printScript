package interpreter;

import statement.Statement;
import visitor.ExpressionVisitor;

import java.util.List;

public interface Interpreter{
    void interpret(List<Statement> statements);
}
