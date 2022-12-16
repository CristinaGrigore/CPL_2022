package cool.ast;

import cool.structures.IdSymbol;
import cool.structures.Scope;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

// Identificatori
public class Id extends Expression {
    private IdSymbol symbol;
    private Scope scope;
    public Token value;

    public Id(Token token, Token value, ParserRuleContext context) {
        super(token, context);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    IdSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(IdSymbol symbol) {
        this.symbol = symbol;
    }

    Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
