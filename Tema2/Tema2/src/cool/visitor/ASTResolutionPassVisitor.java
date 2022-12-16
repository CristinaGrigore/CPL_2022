package cool.visitor;

import cool.ast.*;
import cool.structures.Scope;
import cool.structures.TypeSymbol;

public class ASTResolutionPassVisitor  implements ASTVisitor<TypeSymbol>{
 Scope scope;
 @Override
 public TypeSymbol visit(ASTclassDef asTclassDef) {
  if(asTclassDef.type == null)
   return null;
  var scope = asTclassDef.type;
  asTclassDef.content.forEach(node -> node.accept(this));
  return null;
 }

 @Override
 public TypeSymbol visit(Id id) {
  return null;
 }

 @Override
 public TypeSymbol visit(Prog prog) {
  return null;
 }

 @Override
 public TypeSymbol visit(Formal formal) {
  return null;
 }

 @Override
 public TypeSymbol visit(ASTclassMemberDef varDef) {
  return null;
 }

 @Override
 public TypeSymbol visit(ASTmethodDef funcDef) {
  return null;
 }

 @Override
 public TypeSymbol visit(ASTclassNode asTclassNode) {
  if(asTclassNode.type == null)
   return null;
  var scope = asTclassNode.type;
  asTclassNode.content.forEach(node -> node.accept(this));
  return null;
 }

 @Override
 public TypeSymbol visit(Assign2 assign2) {
  return null;
 }

 @Override
 public TypeSymbol visit(Int iint) {
  return null;
 }

 @Override
 public TypeSymbol visit(ASTString str) {
  return null;
 }

 @Override
 public TypeSymbol visit(Bool bol) {
  return null;
 }

 @Override
 public TypeSymbol visit(Plus plus) {
  return null;
 }

 @Override
 public TypeSymbol visit(Minus minus) {
  return null;
 }

 @Override
 public TypeSymbol visit(Mult mult) {
  return null;
 }

 @Override
 public TypeSymbol visit(Div div) {
  return null;
 }

 @Override
 public TypeSymbol visit(Paren paren) {
  return null;
 }

 @Override
 public TypeSymbol visit(Negation negation) {
  return null;
 }

 @Override
 public TypeSymbol visit(ASTRelational relational) {
  return null;
 }

 @Override
 public TypeSymbol visit(Assign assign) {
  return null;
 }

 @Override
 public TypeSymbol visit(Not not) {
  return null;
 }

 @Override
 public TypeSymbol visit(IsVoid isVoid) {
  return null;
 }

 @Override
 public TypeSymbol visit(New aNew) {
  return null;
 }

 @Override
 public TypeSymbol visit(ImplicitDispatch implicitDispatch) {
  return null;
 }

 @Override
 public TypeSymbol visit(Dispatch dispatch) {
  return null;
 }

 @Override
 public TypeSymbol visit(If anIf) {
  return null;
 }

 @Override
 public TypeSymbol visit(While aWhile) {
  return null;
 }

 @Override
 public TypeSymbol visit(VarDef varDef) {
  return null;
 }

 @Override
 public TypeSymbol visit(Let let) {
  return null;
 }

 @Override
 public TypeSymbol visit(CaseOption caseOption) {
  return null;
 }

 @Override
 public TypeSymbol visit(Case aCase) {
  return null;
 }

 @Override
 public TypeSymbol visit(Block block) {
  return null;
 }

 @Override
 public TypeSymbol visit(Type type) {
  return null;
 }

 @Override
 public TypeSymbol visit(FuncCall funcCall) {
  return null;
 }

 @Override
 public TypeSymbol visit(OtherDispatch otherDispatch) {
  return null;
 }

 @Override
 public TypeSymbol visit(Program program) {
  return null;
 }
}
