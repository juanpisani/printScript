package statement.impl;

import statement.Statement;
import visitor.StatementVisitor;

import java.util.List;

public class BlockStatement implements Statement {

    private List<Statement> statements;

    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visitBlockStatement(this);
    }

    public List<Statement> getStatement() {
        return statements;
    }
}
