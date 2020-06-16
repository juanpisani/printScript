package environment;

import exception.InterpretException;
import token.Token;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

import static token.TokenType.*;

public class EnvironmentImplementation implements Environment{

    private Map<String, DeclarationImplementation> values = new HashMap<>();
    private Environment enclosing;

    public EnvironmentImplementation() {
        enclosing = null;
    }

    public EnvironmentImplementation(Environment enclosing) {
        this.enclosing = enclosing;
    }

    @Override
    public Object get(Token name) {
        if (values.containsKey(name.getLexeme())){
            return values.get(name.getLexeme()).getValue();
        }

        if (enclosing != null) return enclosing.get(name);

        throw new InterpretException(name, "Variable not found");
    }

    @Override
    public void add(String name, TokenType keyword, TokenType type, Object value) {
        values.put(name, new DeclarationImplementation(keyword, type, value));
    }

    @Override
    public Environment getEnclosing() {
        return enclosing;
    }

    @Override
    public void assign(Token name, Object value) {
        if (values.containsKey(name.getLexeme())) {
            DeclarationImplementation declaration = values.get(name.getLexeme());
            if(declaration.getKeyword() == LET){
                if (declaration.getType() == BOOLEAN){
                    if (!(value instanceof Boolean)){
                        throw new InterpretException(name, "Expected a boolean");
                    }
                }
                else if (declaration.getType() == NUMBER_TYPE){
                    if (!(value instanceof Number)) {
                        throw new InterpretException(name, "Expected a number");
                    }
                }
                else if (declaration.getType() == STRING_TYPE){
                    if (!(value instanceof String)){
                        throw new InterpretException(name, "Expected a string");
                    }
                }
                declaration.setValue(value);
                values.put(name.getLexeme(), declaration);
                return;
            } else {
                throw new InterpretException(name, "Constant cannot be changed");
            }
        }
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }
        throw new InterpretException(name, "Undefined variable '" + name.getLexeme() + "'.");
    }
}
