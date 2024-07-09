package tuandoan.treewalkinterpreter.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    private final Expr.Function functionObject;
    private final Environment closure;
    private final String name;


    LoxFunction (String name, Expr.Function functionObject, Environment closure) {
        this.name = name;
        this.functionObject = functionObject;
        this.closure = closure;
    }

    @Override
    public int arity() {
        return this.functionObject.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
        for (int i = 0; i < arguments.size(); ++i) {
            environment.define(functionObject.params.get(i).lexeme, arguments.get(i));
        }
        try {
            interpreter.executeBlock(functionObject.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }

        return null;
    }

    @Override
    public String toString() {
        return "<fn " + name + ">";
    }
}
