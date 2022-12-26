package cool.ast;

import cool.structures.IdSymbol;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class  Formal extends ASTNode {
    public Token type;
    public Token name;
    public IdSymbol symbol;
    public Formal(Token token, Token type, Token name, ParserRuleContext context) {
        super(token, context);
        this.type = type;
        this.name = name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

 public void setIdSymbol(IdSymbol formalSymbol) {
        this.symbol = formalSymbol;
 }
}