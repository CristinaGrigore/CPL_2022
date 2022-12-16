package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Block extends Expression {
    public List<Expression> expressions;
    public Block(Token token, List<Expression> expr)
    {
        super(token);
        this.expressions = expr;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
