package cool.ast;

import cool.visitor.ASTVisitor;

import java.util.List;

public class Let extends Expression{
    public List<VarDef> members;
    public Expression body;

    public Let(List<VarDef> members, Expression body) {
        super("let");
        this.members = members;
        this.body = body;

    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
