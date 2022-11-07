package cool.ast;

import cool.visitor.ASTVisitor;

public class Instruction extends ASTNode {
    public Instruction(String name) {
        super(name);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
