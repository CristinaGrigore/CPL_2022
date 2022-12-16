package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Assign2 extends Expression {
    public Expression name;
    public Expression e;
    public Assign2(Token token, Expression name, Expression e, ParserRuleContext context) {
        super(token, context);
        this.name = name;
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}