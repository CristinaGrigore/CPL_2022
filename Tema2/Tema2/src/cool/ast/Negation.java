package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class Negation extends Expression {
    public Token op;
    public Expression e;
    public Negation(Token token, Token op, Expression e) {
        super(token);
        this.op = op;
        this.e = e;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}