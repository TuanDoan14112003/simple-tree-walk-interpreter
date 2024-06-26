package tuandoan.treewalkinterpreter.lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    final Environment enclosing;

    Environment () {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    private final Map<String, Map<String, Object>> values = new HashMap<>();

    void define(String name, Object value, boolean initialized) {
        Map<String, Object> mapValue = new HashMap<>();
        mapValue.put("value", value);
        mapValue.put("initialized", initialized);
        values.put(name, mapValue);
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            Map<String,Object> mapValue = values.get(name.lexeme);
            if ((boolean) mapValue.get("initialized")) {
                return mapValue.get("value");
            } else {
                throw new RuntimeError(name, "Uninitialized variable '" + name.lexeme + "'.");
            }
        }

        if (enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            Map<String, Object> mapValue = Map.of("value", value,"initialized", true);
            values.put(name.lexeme, mapValue);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }
}
