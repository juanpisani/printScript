package statement.impl;

import expression.Expression;
import statement.Statement;
import visitor.StatementVisitor;

public class PrintStatement implements Statement {

    private Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visitPrintStatement(this);
    }

    public Expression getExpression() {
        return expression;
    }
}
