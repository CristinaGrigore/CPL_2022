package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Program extends ASTNode {
 public LinkedList<ASTNode> stmts;

 Program(LinkedList<ASTNode> stmts, Token token, ParserRuleContext context) {
  super(token, context);
  this.stmts = stmts;
 }

 public <T> T accept(ASTVisitor<T> visitor) {
  return visitor.visit(this);
 }
}
