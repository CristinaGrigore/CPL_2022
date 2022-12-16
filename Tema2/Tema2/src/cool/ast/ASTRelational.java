package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ASTRelational extends Expression {
    public Expression left;
    public Expression right;
    public Token value;
    public ASTRelational(Token token, Token value, Expression left, Expression right, ParserRuleContext context) {
        super(token, context);
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
