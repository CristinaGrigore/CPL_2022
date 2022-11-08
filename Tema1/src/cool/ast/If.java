package cool.ast;

import cool.visitor.ASTVisitor;

public class If extends Expression {
    public Expression cond;
    public Expression thenBranch;
    public Expression elseBranch;
    public If(Expression cond, Expression thenBranch,
                      Expression elseBranch) {
        super("if");
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
