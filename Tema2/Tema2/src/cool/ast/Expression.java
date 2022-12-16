package cool.ast;

import org.antlr.v4.runtime.Token;

public abstract class Expression extends Instruction {
    Expression(Token token) {
        super(token);
    }
}
