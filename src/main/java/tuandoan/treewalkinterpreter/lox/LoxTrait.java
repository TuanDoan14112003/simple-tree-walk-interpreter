package tuandoan.treewalkinterpreter.lox;

import java.util.Map;

class LoxTrait {
    final Token name;
    final Map<String, LoxFunction> methods;

    LoxTrait(Token name, Map<String, LoxFunction> methods) {
        this.name = name;
        this.methods = methods;
    }


    @Override
    public String toString() {
        return name.lexeme;
    }
}
