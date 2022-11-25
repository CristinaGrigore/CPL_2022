package cool.ast;

import cool.visitor.ASTVisitor;

public class While extends Expression {
    public Expression cond;
    public Expression body;
    public While(Expression cond, Expression body) {
        super("while");
        this.cond = cond;
        this.body = body;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
