package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Case extends Expression {
    public Expression name;
    public List<CaseOption> options;
    public Case(Token token, Expression name, List<CaseOption> options) {
        super(token);
        this.name = name;
        this.options = options;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
