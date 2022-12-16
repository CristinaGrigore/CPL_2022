package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Let extends Expression{
    public List<VarDef> members;
    public Expression body;

    public Let(Token token, List<VarDef> members, Expression body, ParserRuleContext context) {
        super(token, context);
        this.members = members;
        this.body = body;

    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
