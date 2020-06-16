package expression.impl;

import expression.Expression;
import token.Token;
import visitor.ExpressionVisitor;

public class VariableExpression implements Expression {

    private Token name;

    public VariableExpression(Token name) {
        this.name = name;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {

        return expressionVisitor.visitVariable(this);
    }

    public Token getName() {
        return name;
    }
}
