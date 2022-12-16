package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class New extends Expression {
    public Token e;

    public New(Token token, Token e) {
        super(token);
        this.e = e;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
