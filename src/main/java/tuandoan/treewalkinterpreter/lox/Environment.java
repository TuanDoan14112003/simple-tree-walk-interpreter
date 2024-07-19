package tuandoan.treewalkinterpreter.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Environment {
    final Environment enclosing;
    private final List<Object> values = new ArrayList<>();

    Environment () {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }


    void define(Object value) {
        values.add(value);
    }


    Object getAt(int distance, int variableIndex) {
        return ancestor(distance).values.get(variableIndex);
    }

    Environment ancestor(int distance) {
        Environment environment = this;
        for (int i = 0; i < distance; i++) {
            environment = environment.enclosing;
        }

        return environment;
    }

    void assignAt(int distance, int index, Object value) {
        ancestor(distance).values.set(index, value);
    }

}
