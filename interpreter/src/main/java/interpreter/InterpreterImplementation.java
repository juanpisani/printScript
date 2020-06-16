package interpreter;

import environment.Environment;
import environment.EnvironmentImplementation;
import exception.InterpretException;
import expression.Expression;
import expression.impl.*;
import statement.Statement;
import statement.impl.*;
import token.Token;
import visitor.ExpressionVisitor;
import visitor.StatementVisitor;

import java.util.List;

import static token.TokenType.*;

public class InterpreterImplementation implements Interpreter, ExpressionVisitor, StatementVisitor {

    private Environment environment = new EnvironmentImplementation();

    @Override
    public void interpret(List<Statement> statements) {
        statements.forEach(s -> s.accept(this));
    }

    @Override
    public Object visitBinary(BinaryExpression expression) {
        Object left = evaluate(expression.getLeft());
        Object right = evaluate(expression.getRight());

        switch (expression.getOperator().getType()) {
            case GREATER:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left > (double)right;
            case GREATER_EQUAL:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left >= (double)right;
            case LESS:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left < (double)right;
            case LESS_EQUAL:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left <= (double)right;
            case MINUS:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left - (double)right;
            case PLUS:
                if(left instanceof Number && right instanceof Number){
                    return (double)left + (double)right;
                }
                return left.toString() + right.toString();
            case SLASH:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left / (double)right;
            case STAR:
                checkNumberOperands(expression.getOperator(), left, right);
                return (double)left * (double)right;
        }

        // Unreachable.
        return null;
    }

    @Override
    public Object visitGrouping(GroupingExpression expression) {
        return evaluate(expression.getExpression());
    }

    @Override
    public Object visitLiteral(LiteralExpression expression) {
        return expression.getValue();
    }

    @Override
    public Object visitUnary(UnaryExpression expression) {
        Object right = evaluate(expression.getRight());

        if (expression.getOperator().getType() == MINUS) {
            checkNumberOperand(expression.getOperator(), right);
            return -(double) right;
        }

        // Unreachable.
        return null;
    }

    @Override
    public Object visitVariable(VariableExpression expression) {
        return environment.get(expression.getName());
    }

    @Override
    public Object visitAssignment(AssigmentExpression expression) {
        Object value = evaluate(expression.getExpression());

        environment.assign(expression.getName(), value);
        return value;
    }

    @Override
    public void visitExpressionStatement(ExpressionStatement statement) {
        evaluate(statement.getExpression());
    }

    @Override
    public void visitPrintStatement(PrintStatement statement) {
        Object value = evaluate(statement.getExpression());
        System.out.println(value);
    }

    @Override
    public void visitVariableStatement(VariableStatement statement) {
        Object value = null;
        if (statement.getExpression() != null){
            value = evaluate(statement.getExpression());
        }
        if (value == null){
            environment.add(statement.getName().getLexeme(), statement.getKeyWord().getType(), statement.getType(), null);
            return;
        }
        if (statement.getType() == BOOLEAN){
            if (!(value instanceof Boolean)){
                throw new InterpretException(statement.getName(), "Expected a Boolean");
            }
        }
        if (statement.getType() == NUMBER_TYPE){
            if (!(value instanceof Number)){
                throw new InterpretException(statement.getName(), "Expected a Number");
            }
        }
        if (statement.getType() == STRING_TYPE){
            if (!(value instanceof String)){
                throw new InterpretException(statement.getName(), "Expected a String");
            }
        }

        environment.add(statement.getName().getLexeme(), statement.getKeyWord().getType(), statement.getType(), value);
    }

    @Override
    public void visitBlockStatement(BlockStatement statement) {
        executeBlock(statement.getStatement(), new EnvironmentImplementation(environment));
    }

    private void executeBlock(List<Statement> statements, Environment environment) {
        Environment previous = this.environment;
        try {
            this.environment = environment;

            for (Statement statement : statements) {
                statement.accept(this);
            }
        } finally {
            this.environment = previous;
        }
    }

    @Override
    public void visitIfStatement(IfStatement statement) {
        if (isTruthy(evaluate(statement.getCondition()))) {
            statement.getThenDo().accept(this);
        } else if (statement.getElseDo() != null) {
            statement.getElseDo().accept(this);
        }
    }

    private Object evaluate(Expression expression) {
        return expression.accept(this);
    }

    private boolean isTruthy(Object object) {
        if (object == null) return false;
        if (object instanceof Boolean) return (boolean)object;
        return true;
    }

    private void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Double) return;
        throw new InterpretException(operator, "Operand must be a number.");
    }

    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double) return;

        throw new InterpretException(operator, "Operands must be numbers.");
    }

}
