package cool.ast;

import cool.visitor.ASTVisitor;

public class Assign extends Expression {
    public Expression name;
    public Expression e;

    public Assign(Expression left, Expression right) {
        super("=");
        this.name = left;
        this.e = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
