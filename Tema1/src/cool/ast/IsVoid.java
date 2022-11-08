package cool.ast;

import cool.visitor.ASTVisitor;

public class IsVoid extends Expression {
    public Expression expr;
    public IsVoid(Expression expr) {
        super("isvoid");
        this.expr = expr;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
