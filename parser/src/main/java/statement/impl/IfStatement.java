package statement.impl;

import expression.Expression;
import statement.Statement;
import visitor.StatementVisitor;

public class IfStatement implements Statement {

    private Expression condition;
    private Statement thenDo, elseDo;

    public IfStatement(Expression condition, Statement thenDo, Statement elseDo) {
        this.condition = condition;
        this.thenDo = thenDo;
        this.elseDo = elseDo;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visitIfStatement(this);
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getThenDo() {
        return thenDo;
    }

    public Statement getElseDo() {
        return elseDo;
    }
}
