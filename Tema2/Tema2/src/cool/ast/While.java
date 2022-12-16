package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class While extends Expression {
    public Expression cond;
    public Expression body;
    public While(Token token, Expression cond, Expression body) {
        super(token);
        this.cond = cond;
        this.body = body;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
