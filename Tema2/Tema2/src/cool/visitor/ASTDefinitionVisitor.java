package cool.visitor;

import cool.ast.*;
import cool.structures.*;

import javax.lang.model.util.Types;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ASTDefinitionVisitor  implements ASTVisitor<Void> {
 int indent = 0;
 private Scope scope;
 Scope currentScope = null;
 @Override
 public Void visit(ASTclassDef asTclassDef) {

  printIndent("class");
  indent+=2;
  printIndent(asTclassDef.name.getText());
  if(asTclassDef.name2 != null)
   printIndent(asTclassDef.name2.getText());
  indent-=2;
  return null;
 }

 @Override
 public Void visit(Id id) {
  var symbol = currentScope.lookup(id.getToken().getText());
  if (symbol == null) {
   ASTVisitor.error(id.getToken(),
           id.getToken().getText() + " undefined");
   return null;
  }

  // Atașăm simbolul nodului din arbore.
  id.setSymbol((IdSymbol)symbol);
  printIndent(id.value.getText());
  return null;
 }

 @Override
 public Void visit(Prog prog) {
  currentScope = new DefaultScope(null);

  prog.class_definitions.forEach(def -> def.accept(this));

  return null;
 }

 @Override
 public Void visit(Formal formal) {

  return null;
 }

 @Override
 public Void visit(ASTclassMemberDef varDef) {
  var id = varDef.name;
  var symbol = new IdSymbol(id.getText());

  if (!currentScope.add(symbol)) {
   ASTVisitor.error(id,
           "var " + id.getText() + " redefined"
   );
   return null;
  }

  if(varDef.init != null)
   varDef.init.accept(this);

  return null;
 }


 @Override
 public Void visit(ASTmethodDef funcDef) {

  funcDef
          .params
          .stream()
          .map(this::visit)
          .collect(Collectors.toList());

  funcDef.body
          .stream()
          .map(expr -> expr.accept(this))
          .collect(Collectors.toList());

  return null;
 }

 @Override
 public Void visit(ASTclassNode asTclassNode) {
  var symbol = currentScope.lookup(asTclassNode.getToken().getText());
  var className = asTclassNode.name.getText();
  var parentName = asTclassNode.name2 == null ? "" : asTclassNode.name2.getText();
  var context = asTclassNode.getContext();
  if(className.equals("SELF_TYPE"))
  {
    SymbolTable.error(context,
            asTclassNode.name,
                      "Class has illegal name " + className
    );
    return null;
  }
  if(parentName.equals("SELF_TYPE") || parentName.equals("Int")
  || parentName.equals("String") || parentName.equals("Bool"))
  {
    SymbolTable.error(context,
            asTclassNode.name,
            "Class " + className + " has illegal parent " + parentName
    );
    return null;
  }
  var type = new TypeSymbol(className, parentName);
  if (!SymbolTable.globals.add(type)) {
   SymbolTable.error(
           asTclassNode.getContext(),
           asTclassNode.name,
           "Class " + className + " is redefined"
   );
   return null;
  }
  asTclassNode.type = type;
  scope = type;
  asTclassNode.content.forEach(
          instr -> {
            instr.accept(this);
           });
  return null;
 }

 @Override
 public Void visit(Assign2 assign2) {

  assign2.name.accept(this);
  assign2.e.accept(this);
  return null;
 }

 @Override
 public Void visit(Int iint) {

  return null;
 }

 @Override
 public Void visit(ASTString str) {

  return null;
 }

 @Override
 public Void visit(Bool bol) {

  return null;
 }

 @Override
 public Void visit(Plus plus) {

  plus.left.accept(this);
  plus.right.accept(this);
  return null;
 }

 @Override
 public Void visit(Minus minus) {

  minus.left.accept(this);
  minus.right.accept(this);
  return null;
 }

 @Override
 public Void visit(Mult mult) {

  mult.left.accept(this);
  mult.right.accept(this);

  return null;
 }

 @Override
 public Void visit(Div div) {

  div.left.accept(this);
  div.right.accept(this);
  return null;
 }

 @Override
 public Void visit(Paren paren) {
  paren.e.accept(this);
  return null;
 }

 @Override
 public Void visit(Negation negation) {

  negation.e.accept(this);
  return null;
 }

 @Override
 public Void visit(ASTRelational relational) {

  relational.left.accept(this);
  relational.right.accept(this);
  return null;
 }

 @Override
 public Void visit(Assign assign) {
  assign.name.accept(this);
  assign.e.accept(this);
  return null;
 }

 @Override
 public Void visit(Not not) {
  not.e.accept(this);
  return null;
 }

 @Override
 public Void visit(IsVoid isVoid) {
  isVoid.expr.accept(this);
  return null;
 }

 @Override
 public Void visit(New aNew) {

  return null;
 }

 @Override
 public Void visit(ImplicitDispatch implicitDispatch) {

  implicitDispatch.args.forEach(expr -> expr.accept(this));
  return null;
 }

 @Override
 public Void visit(Dispatch dispatch) {
  dispatch.left.accept(this);
  dispatch.call.accept(this);
  return null;
 }

 @Override
 public Void visit(If anIf) {
  anIf.cond.accept(this);
  anIf.thenBranch.accept(this);
  if(anIf.elseBranch != null) {
   anIf.elseBranch.accept(this);
  }
  return null;
 }

 @Override
 public Void visit(While aWhile) {
  aWhile.cond.accept(this);
  aWhile.body.accept(this);
  return null;
 }

 @Override
 public Void visit(VarDef varDef) {

  if(varDef.expr != null)
   varDef.expr.accept(this);
  return null;
 }

 @Override
 public Void visit(Let let) {

  if(let.members != null)
   let.members.forEach(member -> member.accept(this));
  if(let.body != null)
   let.body.accept(this);

  return null;
 }

 @Override
 public Void visit(CaseOption caseOption) {
  if(caseOption.value != null)
   caseOption.value.accept(this);
  return null;
 }

 @Override
 public Void visit(Case aCase) {
  aCase.name.accept(this);
  aCase.options.forEach(opt -> opt.accept(this));
  return null;
 }

 @Override
 public Void visit(Block block) {

  block.expressions.forEach(e -> e.accept(this));
  return null;
 }

 @Override
 public Void visit(Type type) {

  return null;
 }

 @Override
 public Void visit(FuncCall funcCall) {

  funcCall.args.forEach(expr -> expr.accept(this));
  return null;
 }

 @Override
 public Void visit(OtherDispatch otherDispatch) {

  otherDispatch.left.accept(this);
  otherDispatch.call.accept(this);

  return null;
 }

 @Override
 public Void visit(Program program) {
  return null;
 }


 void printIndent(String str) {
  for (int i = 0; i < indent; i++)
   System.out.print(" ");
  System.out.println(str);
 }

}
