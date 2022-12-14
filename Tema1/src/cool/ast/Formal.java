package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class  Formal extends ASTNode {
    public Token type;
    public Token name;

    public Formal(Token type, Token name) {
        super("formal");
        this.type = type;
        this.name = name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}