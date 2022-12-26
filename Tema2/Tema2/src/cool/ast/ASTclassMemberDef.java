package cool.ast;

import cool.structures.IdSymbol;
import cool.structures.TypeSymbol;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTclassMemberDef extends Instruction {
    public Token name;
    public Token type;
    public Expression init;
    public IdSymbol id_symbol;
    public ASTclassMemberDef(Token token, Token name, Token type, Expression init, ParserRuleContext context) {
        super(token, context);
        this.name = name;
        this.type = type;
        this.init = init;

    }
    public IdSymbol getSymbol() {
        return id_symbol;
    }
    public void setSymbol(IdSymbol type) {
        this.id_symbol = type;
    }
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

