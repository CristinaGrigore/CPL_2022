package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class Minus extends Expression {
    public Expression left;
    public Expression right;

    public Minus(Token token, Expression left, Expression right) {
        super(token);
        this.left = left;
        this.right = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
