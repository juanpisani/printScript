package statement.impl;

import expression.Expression;
import statement.Statement;
import visitor.StatementVisitor;

public class ExpressionStatement implements Statement {

    private Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visitExpressionStatement(this);
    }

    public Expression getExpression() {
        return expression;
    }
}
