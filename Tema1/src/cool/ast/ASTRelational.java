package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class ASTRelational extends Expression {
    public Expression left;
    public Expression right;

    public ASTRelational(Token start, Expression left, Expression right) {
        super(start.getText());
        this.left = left;
        this.right = right;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
