package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ImplicitDispatch extends Expression {
    public Token name;
    public List<Expression> args;
    public ImplicitDispatch(Token token, Token name, List<Expression> args, ParserRuleContext context) {
        super(token, context);
        this.name = name;
        this.args = args;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
