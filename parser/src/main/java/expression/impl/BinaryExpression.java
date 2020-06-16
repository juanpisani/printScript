package expression.impl;

import expression.Expression;
import token.Token;
import visitor.ExpressionVisitor;

public class BinaryExpression implements Expression {

    private Expression left, right;
    private Token operator;

    public BinaryExpression(Expression left, Expression right, Token operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Object accept(ExpressionVisitor expressionVisitor) {
        return expressionVisitor.visitBinary(this);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public Token getOperator() {
        return operator;
    }
}
