package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class If extends Expression {
    public Expression cond;
    public Expression thenBranch;
    public Expression elseBranch;
    public If(Token token, Expression cond, Expression thenBranch,
              Expression elseBranch) {
        super(token);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
