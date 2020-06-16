package statement.impl;

import expression.Expression;
import statement.Statement;
import token.Token;
import token.TokenType;
import visitor.StatementVisitor;

public class VariableStatement implements Statement {

    private Token name;
    private Expression expression;
    private TokenType type;
    private Token keyWord;

    public VariableStatement(Token name, Expression expression, TokenType type, Token keyWord) {
        this.name = name;
        this. expression = expression;
        this.type = type;
        this.keyWord = keyWord;
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visitVariableStatement(this);
    }

    public TokenType getType() {
        return type;
    }

    public Token getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    public Token getKeyWord() {
        return keyWord;
    }
}
