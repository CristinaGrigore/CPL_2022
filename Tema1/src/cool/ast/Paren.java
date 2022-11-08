package cool.ast;

import cool.visitor.ASTVisitor;

public class Paren extends Expression {
    public Expression e;
    public Paren(Expression e) {
        super("paren");
        this.e = e;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
