package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class Bool extends Expression {
    public Bool(Token value) {
        super(value.getText());
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
