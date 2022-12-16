package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends Instruction {
    Expression(Token token, ParserRuleContext context) {
        super(token, context);
    }
}
