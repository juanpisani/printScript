package statement;

import visitor.StatementVisitor;

public interface Statement {
    void accept(StatementVisitor statementVisitor);
}
