package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public class VarDef extends ASTNode {
    public Token type;
    public Expression expr;
    public Token id;

 public VarDef(Token type, Token id, Expression expr, Token token) {
  super(token);
  this.type = type;
  this.id = id;
  this.expr = expr;
 }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
