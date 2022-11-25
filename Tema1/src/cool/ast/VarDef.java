package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class VarDef extends ASTNode {
    public Token name;
    public Token type;
    public Expression expr;

    public VarDef(Token name, Token type, Expression expr) {
        super("local");
        this.name = name;
        this.type = type;
        this.expr = expr;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
