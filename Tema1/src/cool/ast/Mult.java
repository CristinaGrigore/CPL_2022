package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class Mult extends Expression {
    public Expression left;
    public Expression right;

    public Mult(Expression left, Expression right) {
        super("mult");
        this.left = left;
        this.right = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
