package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Assign extends Expression {
    public Expression name;
    public Expression e;

    public Assign(Token token, Expression left, Expression right, ParserRuleContext context) {
        super(token, context);
        this.name = left;
        this.e = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
