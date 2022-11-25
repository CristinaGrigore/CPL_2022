package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class CaseOption extends ASTNode {
    public Token name;
    public Token type;
    public Expression value;
    public CaseOption(Token name, Token type, Expression value) {
        super("case branch");
        this.name = name;
        this.type = type;
        this.value = value;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
