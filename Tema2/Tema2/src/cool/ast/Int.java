package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Int extends Expression {
    public Token value;
    public Int(Token token, Token value, ParserRuleContext context) {
        super(token, context);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
