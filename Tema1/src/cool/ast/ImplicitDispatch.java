package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ImplicitDispatch extends Expression {
    public Token name;
    public List<Expression> args;
    public ImplicitDispatch(Token name, List<Expression> args) {
        super("implicit dispatch");
        this.name = name;
        this.args = args;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
