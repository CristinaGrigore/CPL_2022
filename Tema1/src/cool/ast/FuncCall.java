package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class FuncCall extends Expression {
    public Token name;
    public List<Expression> args;
    public FuncCall(Token name, List<Expression> args) {
        super("function call");
        this.name = name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
