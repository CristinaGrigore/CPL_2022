package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;
public class Not extends Expression {
    public Token op;
    public Expression e;
    public Not(Token op, Expression e) {
        super(op.getText());
        this.op = op;
        this.e = e;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
