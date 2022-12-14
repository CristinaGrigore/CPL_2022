package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class IsVoid extends Expression {
    public Expression expr;
    public IsVoid(Token token, Expression expr, ParserRuleContext context) {
        super(token, context);
        this.expr = expr;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
