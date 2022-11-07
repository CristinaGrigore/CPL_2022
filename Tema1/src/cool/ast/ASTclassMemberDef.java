package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTclassMemberDef extends Instruction {
    public Token name;
    public Token type;
    public Expression init;

    public ASTclassMemberDef(Token name, Token type, Expression init) {
        super("attribute");
        this.name = name;
        this.type = type;
        this.init = init;

    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

