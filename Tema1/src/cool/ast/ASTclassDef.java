package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTclassDef extends ASTNode {
    public Token name;
    public Token name2;
    public List<Instruction> content;
    public ASTclassDef(Token name, Token baseClass, List<Instruction> content) {

        super("class");
        this.name = name;
        this.name2 = baseClass;
        this.content = content;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

