package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

// Identificatori
public class Type extends Expression {
    public Type(Token start) {
        super(start.getText());
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
