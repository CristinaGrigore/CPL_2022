package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

// Identificatori
public class Type extends Expression {
    public Token value;
    public Type(Token start) {
        super(start);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
