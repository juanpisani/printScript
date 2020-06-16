package expression.impl;

import expression.Expression;
import token.Token;
import visitor.ExpressionVisitor;

public class AssigmentExpression implements Expression {

    private Expression expression;
    private Token name;

    public AssigmentExpression(Token name, Expression expression) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitAssignment(this);
    }

    public Expression getExpression() {
        return expression;
    }

    public Token getName() {
        return name;
    }
}
