package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public abstract class Instruction extends ASTNode {
    public Instruction(Token name) {
        super(name);
    }

   // public <T> T accept(ASTVisitor<T> visitor) {
//        return visitor.visit(this);
//    }
}
