package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Prog extends ASTNode {
    public List<ASTclassDef> class_definitions;
    public Prog(List<ASTclassDef> class_definitions) {

        super("program");
        this.class_definitions = class_definitions;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
