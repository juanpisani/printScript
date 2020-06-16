package expression.impl;

import expression.Expression;
import visitor.ExpressionVisitor;

public class GroupingExpression implements Expression {

    private Expression expression;

    public GroupingExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitGrouping(this);
    }

    public Expression getExpression() {
        return expression;
    }
}
