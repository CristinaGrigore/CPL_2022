package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class Assign2 extends Expression {
    public Expression value;

    public Assign2(Expression value) {
        super("assign2");
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}