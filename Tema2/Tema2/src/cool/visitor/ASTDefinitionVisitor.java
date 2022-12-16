package cool.visitor;

import cool.ast.*;

import java.util.stream.Collectors;

public class ASTDefinitionVisitor  implements ASTVisitor<Void> {
 int indent = 0;
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
  printIndent(id.value.getText());
  return null;
 }

 @Override
 public Void visit(Prog prog) {
  
  printIndent("program");
  indent+=2;
  prog.class_definitions.forEach(def -> def.accept(this));
  indent-=2;
  return null;
 }

 @Override
 public Void visit(Formal formal) {
  printIndent("formal");
  indent+=2;
  printIndent(formal.name.getText());
  if(formal.type != null)
   printIndent(formal.type.getText());
  indent-=2;
  return null;
 }

 @Override
 public Void visit(ASTclassMemberDef varDef) {
  printIndent("attribute");
  indent+=2;
  printIndent(varDef.name.getText());
  if(varDef.type != null)
   printIndent(varDef.type.getText());
  if(varDef.init != null)
   varDef.init.accept(this);
  indent-=2;
  return null;
 }


 @Override
 public Void visit(ASTmethodDef funcDef) {
  printIndent("method");
  indent+=2;
  printIndent(funcDef.name.getText());

  funcDef
          .params
          .stream()
          .map(this::visit)
          .collect(Collectors.toList());
  if(funcDef.returnType != null)
   printIndent(funcDef.returnType.getText());
  funcDef.body
          .stream()
          .map(expr -> expr.accept(this))
          .collect(Collectors.toList());

  indent -= 2;
  return null;
 }

 @Override
 public Void visit(ASTclassNode asTclassNode) {
  printIndent("class");
  indent+=2;
  printIndent(asTclassNode.name.getText());
  if(asTclassNode.name2 != null)
   printIndent(asTclassNode.name2.getText());
  asTclassNode.content.forEach(
          instr -> {
           if(instr == null)
            System.out.println("NULL");
           else {
            instr.accept(this);
           }});
  indent-=2;
  return null;
 }

 @Override
 public Void visit(Assign2 assign2) {
  printIndent("<-");
  indent += 2;
  assign2.name.accept(this);
  assign2.e.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Int iint) {
  printIndent(iint.value.getText());
  return null;
 }

 @Override
 public Void visit(ASTString str) {
  printIndent(str.value.getText());
  return null;
 }

 @Override
 public Void visit(Bool bol) {
  printIndent(bol.value.getText());
  return null;
 }

 @Override
 public Void visit(Plus plus) {
  printIndent("+");
  indent += 2;
  plus.left.accept(this);
  plus.right.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Minus minus) {
  printIndent("-");
  indent += 2;
  minus.left.accept(this);
  minus.right.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Mult mult) {
  printIndent("*");
  indent += 2;
  mult.left.accept(this);
  mult.right.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Div div) {
  printIndent("/");
  indent += 2;
  div.left.accept(this);
  div.right.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Paren paren) {
  paren.e.accept(this);
  return null;
 }

 @Override
 public Void visit(Negation negation) {
  printIndent(negation.op.getText());
  indent += 2;
  negation.e.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(ASTRelational relational) {

  printIndent(relational.value.getText());
  indent += 2;
  relational.left.accept(this);
  relational.right.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Assign assign) {
  printIndent("=");
  indent += 2;
  assign.name.accept(this);
  assign.e.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Not not) {
  printIndent("not");
  indent += 2;
  not.e.accept(this);
  indent-=2;
  return null;
 }

 @Override
 public Void visit(IsVoid isVoid) {
  printIndent("isvoid");
  indent += 2;
  isVoid.expr.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(New aNew) {
  printIndent("new");
  indent += 2;
  printIndent(aNew.e.getText());
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(ImplicitDispatch implicitDispatch) {
  printIndent("implicit dispatch");
  indent += 2;
  printIndent(implicitDispatch.name.getText());
  implicitDispatch.args.forEach(expr -> expr.accept(this));
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Dispatch dispatch) {
  printIndent(".");
  indent += 2;
  dispatch.left.accept(this);
  dispatch.call.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(If anIf) {
  printIndent("if");
  indent += 2;
  anIf.cond.accept(this);
  anIf.thenBranch.accept(this);
  if(anIf.elseBranch != null) {
   anIf.elseBranch.accept(this);
  }
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(While aWhile) {
  printIndent("while");
  indent += 2;
  aWhile.cond.accept(this);
  aWhile.body.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(VarDef varDef) {
  printIndent("local");
  indent+=2;
  if(varDef.type != null)
   printIndent(varDef.type.getText());
  if(varDef.id != null)
   printIndent(varDef.id.getText());

  if(varDef.expr != null)
   varDef.expr.accept(this);
  indent-=2;
  return null;
 }

 @Override
 public Void visit(Let let) {
  printIndent("let");
  indent += 2;
  if(let.members != null)
   let.members.forEach(member -> member.accept(this));
  if(let.body != null)
   let.body.accept(this);
  indent -= 2;

  return null;
 }

 @Override
 public Void visit(CaseOption caseOption) {
  printIndent("case branch");
  indent += 2;
  if(caseOption.name != null)
   printIndent(caseOption.name.getText());
  if(caseOption.type != null)
   printIndent(caseOption.type.getText());
  if(caseOption.value != null)
   caseOption.value.accept(this);
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Case aCase) {
  printIndent("case");
  indent += 2;
  aCase.name.accept(this);
  aCase.options.forEach(opt -> opt.accept(this));
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Block block) {
  printIndent("block");
  indent += 2;
  block.expressions.forEach(e -> e.accept(this));
  indent -= 2;
  return null;
 }

 @Override
 public Void visit(Type type) {
  printIndent(type.value.getText());
  return null;
 }

 @Override
 public Void visit(FuncCall funcCall) {
  printIndent(funcCall.name.getText());
  funcCall.args.forEach(expr -> expr.accept(this));
  return null;
 }

 @Override
 public Void visit(OtherDispatch otherDispatch) {
  printIndent(".");

  indent += 2;
  otherDispatch.left.accept(this);
  printIndent(otherDispatch.type.getText());
  otherDispatch.call.accept(this);

  indent -= 2;

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
