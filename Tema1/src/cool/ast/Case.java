package cool.ast;

import cool.visitor.ASTVisitor;

import java.util.List;

public class Case extends Expression {
    public Expression name;
    public List<CaseOption> options;
    public Case(Expression name, List<CaseOption> options) {
        super("case");
        this.name = name;
        this.options = options;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
