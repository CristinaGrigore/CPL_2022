package cool.ast;

import cool.structures.TypeSymbol;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTclassNode extends ASTclassDef {
    public TypeSymbol type;
    public ASTclassNode(Token token, Token name, Token name2, List<Instruction> content, ParserRuleContext context) {
        super(token, name, name2, content, context);

    }
    public TypeSymbol getType() {
        return type;
    }
    public void setType(TypeSymbol type) {
        this.type = type;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
