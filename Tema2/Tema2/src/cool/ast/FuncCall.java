package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class FuncCall extends Expression {
    public Token name;
    public List<Expression> args;
    public FuncCall(Token token, Token name, List<Expression> args) {
        super(token);
        this.name = name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
