package expression.impl;

import expression.Expression;
import visitor.ExpressionVisitor;

public class LiteralExpression implements Expression {

    private Object value;

    public LiteralExpression(Object value) {
        this.value = value;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitLiteral(this);
    }

    public Object getValue() {
        return value;
    }
}
