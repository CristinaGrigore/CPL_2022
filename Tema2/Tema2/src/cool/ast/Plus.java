package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class Plus extends Expression {
    public Expression left;
    public Expression right;

    public Plus(Token token, Expression left, Expression right) {
        super(token);
        this.left = left;
        this.right = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
