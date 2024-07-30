package tuandoan.treewalkinterpreter.lox;

class LoxProperty {
    private final Stmt.Property declaration;
    private final Environment closure;


    LoxProperty (Stmt.Property declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }


    Object execute(LoxInstance instance, Interpreter interpreter) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        Environment innerEnvironment = new Environment(environment);
        try {
            interpreter.executeBlock(declaration.body, innerEnvironment);
        } catch (Return returnValue) {
            return returnValue.value;
        }

        return null;
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
