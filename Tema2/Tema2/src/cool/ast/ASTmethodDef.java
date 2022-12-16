package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ASTmethodDef extends Instruction {
    public Token name;
    public List<Formal> params;

    public Token returnType;
    public List<Expression> body;

    public ASTmethodDef(Token token, Token type, Token name, List<Formal> formals, List<Expression> body, ParserRuleContext context) {
        super(token, context);
        this.returnType = type;
        this.name = name;
        this.params = formals;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
