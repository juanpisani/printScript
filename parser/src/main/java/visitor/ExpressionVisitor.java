package visitor;

import expression.impl.*;

public interface ExpressionVisitor {
    Object visitBinary(BinaryExpression expression);
    Object visitGrouping(GroupingExpression expression);
    Object visitLiteral(LiteralExpression expression);
    Object visitUnary(UnaryExpression expression);
    Object visitVariable(VariableExpression expression);
    Object visitAssignment(AssigmentExpression expression);
}
