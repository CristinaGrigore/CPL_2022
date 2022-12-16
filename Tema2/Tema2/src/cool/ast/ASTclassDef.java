package cool.ast;

import cool.structures.TypeSymbol;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTclassDef extends ASTNode {
    public Token name;
    public Token name2;
    public List<Instruction> content;
    public TypeSymbol type;
    public ASTclassDef(Token token, Token name, Token baseClass, List<Instruction> content, ParserRuleContext context) {
        super(token, context);
        this.name = name;
        this.name2 = baseClass;
        this.content = content;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

}

