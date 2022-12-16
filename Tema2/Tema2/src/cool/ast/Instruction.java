package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Instruction extends ASTNode {
    public Instruction(Token name, ParserRuleContext context) {
        super(name, context);
    }

   // public <T> T accept(ASTVisitor<T> visitor) {
//        return visitor.visit(this);
//    }
}
