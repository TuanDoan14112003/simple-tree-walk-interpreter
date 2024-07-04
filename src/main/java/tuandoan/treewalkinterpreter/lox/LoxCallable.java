package tuandoan.treewalkinterpreter.lox;

import java.util.List;

interface LoxCallable {
    Object call(Interpreter interpreter, List<Object> arguments);
}
