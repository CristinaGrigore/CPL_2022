package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class VarDef extends ASTNode {
    public Token type;
    public Expression expr;
    public Token id;

 public VarDef(Token type, Token id, Expression expr, Token token, ParserRuleContext context) {
  super(token, context);
  this.type = type;
  this.id = id;
  this.expr = expr;
 }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
