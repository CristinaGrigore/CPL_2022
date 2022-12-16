package cool.ast;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class ASTNode {
    // Reținem un token descriptiv, pentru a putea afișa ulterior
    // informații legate de linia și coloana eventualelor erori semantice.
    public Token token;
    private final ParserRuleContext context;
    ASTNode(Token name, ParserRuleContext context) {
        this.token = name;
        this.context = context;
    }

    public Token getToken() {
        return token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }

    public ParserRuleContext getContext() {
        return context;
    }
}
