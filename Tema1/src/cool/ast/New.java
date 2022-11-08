package cool.ast;

import cool.visitor.ASTVisitor;

public class New extends Expression {
    public Expression e;

    public New(Expression e) {
        super("new");
        this.e = e;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
