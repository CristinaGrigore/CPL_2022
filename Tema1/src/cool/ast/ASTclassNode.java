package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTclassNode extends ASTclassDef {
    public ASTclassNode(Token name, Token name2, List<Instruction> content) {
        super(name, name2, content);

    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
