package cool.ast;

import cool.visitor.ASTVisitor;

import java.util.List;

public class Block extends Expression {
    public List<Expression> expressions;
    public Block(List<Expression> expr)
    {
        super("block");
        this.expressions = expr;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
