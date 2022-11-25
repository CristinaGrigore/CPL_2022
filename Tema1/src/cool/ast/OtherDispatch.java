package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class OtherDispatch extends Expression {
    public Expression left;
    public Token type;
    public FuncCall call;
    public OtherDispatch(Expression name, Token type, FuncCall call) {
        super("@");
        this.left = name;
        this.type = type;
        this.call = call;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
