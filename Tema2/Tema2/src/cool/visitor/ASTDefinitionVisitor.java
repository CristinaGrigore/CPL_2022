package cool.visitor;

import cool.ast.*;
import cool.structures.*;

import javax.lang.model.util.Types;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ASTDefinitionVisitor  implements ASTVisitor<Void> {
 int indent = 0;
 Scope currentScope = null;
 @Override
 public Void visit(ASTclassDef asTclassDef) {
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
  var id = formal.name.getText();
  var methodScope = (MethodSymbol)currentScope;
  var classScope = (TypeSymbol)currentScope.getParent();

  if (id.equals("self")) {
   SymbolTable.error(
           formal.getContext(),
           formal.name,
           "Method " + methodScope.getName() + " of class "  + classScope.getName()
                   + " has formal parameter with illegal name " + id
   );
   return null;
  }

  var formalType = formal.type.getText();
  if (formalType.equals("SELF_TYPE")) {
   SymbolTable.error(
           formal.getContext(),
           formal.type,
           "Method " + methodScope.getName() + " of class "  + classScope.getName()
                   + " has formal parameter " + id + " with illegal type " + formalType
   );
   return null;
  }

  var formalSymbol = new IdSymbol(id);
  formalSymbol.setType(new TypeSymbol(formalType, null));

  if (!currentScope.add(formalSymbol)) {
   SymbolTable.error(
           formal.getContext(),
           formal.name,
           "Method " + methodScope + " of class " + classScope
                   + " redefines formal parameter " + id
   );
   return null;
  }
  formal.setIdSymbol(formalSymbol);
  return null;
 }

 @Override
 public Void visit(ASTclassMemberDef varDef) {
  var id = varDef.name;
  var symbol = new IdSymbol(id.getText());
  var context = varDef.getContext();

  if(varDef.name.getText().equals("self"))
  {
   SymbolTable.error(context,
           varDef.name,
           "Class " + ((TypeSymbol)currentScope).getName() + " has attribute with illegal name " + varDef.name.getText()
   );
   return null;
  }
  if (!currentScope.add(symbol)) {
   SymbolTable.error(context,
           id,
           "Class " + ((TypeSymbol) currentScope).getName() + " redefines attribute " + id.getText()
   );
   return null;
  }
   var attribType = varDef.type.getText();
   var typeSymbol = SymbolTable.globals.lookup(attribType);

   symbol.setType((TypeSymbol) typeSymbol);
   varDef.setSymbol(symbol);

  if(varDef.init != null)
   varDef.init.accept(this);

  return null;
 }


 @Override
 public Void visit(ASTmethodDef funcDef) {
  var id = funcDef.name;
  var symbol = new MethodSymbol(id.getText());
  var context = funcDef.getContext();
  symbol.setParent((TypeSymbol) currentScope);

  if(!((TypeSymbol) currentScope).addMethod(symbol))
  {
   SymbolTable.error(context,
                     id,
                      "Class " + ((TypeSymbol)currentScope).getName() + " redefines method " + id.getText()
   );
   return null;
  }

 funcDef.methodSymbol = symbol;

 //switch to method scope
 currentScope = symbol;
  funcDef
          .params
          .stream()
          .map(this::visit)
          .collect(Collectors.toList());

  funcDef.body
          .stream()
          .map(expr -> expr.accept(this))
          .collect(Collectors.toList());
  //return to class' scope
  currentScope = currentScope.getParent();
  return null;
 }

 @Override
 public Void visit(ASTclassNode asTclassNode) {
  var symbol = currentScope.lookup(asTclassNode.getToken().getText());
  var className = asTclassNode.name.getText();
  var parentName = asTclassNode.name2 == null ? "Object" : asTclassNode.name2.getText();
  var context = asTclassNode.getContext();

  if(className.equals("SELF_TYPE"))
  {
    SymbolTable.error(context,
            asTclassNode.name,
                      "Class has illegal name " + className
    );
    return null;
  }

  var type = new TypeSymbol(className,
          parentName);
  if (!SymbolTable.globals.add(type)) {
   SymbolTable.error(
           asTclassNode.getContext(),
           asTclassNode.name,
           "Class " + className + " is redefined"
   );
   return null;
  }
  if(parentName.equals("SELF_TYPE") || parentName.equals("Int")
  || parentName.equals("String") || parentName.equals("Bool"))
  {
    SymbolTable.error(context,
            asTclassNode.name2,
            "Class " + className + " has illegal parent " + parentName
    );
    return null;
  }
  var parentType = SymbolTable.globals.lookup(type.getParentName());

 // type.setParent((TypeSymbol)parentType);
 // System.out.println("class " + type.getName() + " parent " + parentType.getName());
  asTclassNode.type = type;
  currentScope = type;
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
  var context = plus.getContext();
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
  var symbol = currentScope.lookup(let.getToken().getText());
  var context = let.getContext();
  for(var member : let.members)
  {
   if(member.id.getText().compareTo("self") == 0) {
    SymbolTable.error(context,
            member.id,
            "Let variable has illegal name self"
    );
   // return null;
   }
   if(SymbolTable.globals.lookup(member.type.getText()) == null)
   {
    SymbolTable.error(context,
            member.type,
            "Let variable " + member.id.getText()  + " has undefined type " + member.type.getText()
    );
  //  return null;
   }
  }

  if(let.members != null)
   let.members.forEach(member -> member.accept(this));
  if(let.body != null)
   let.body.accept(this);

  return null;
 }

 @Override
 public Void visit(CaseOption caseOption) {
  var context = caseOption.getContext();

   if(caseOption.name.getText().compareTo("self") == 0) {
    SymbolTable.error(context,
            caseOption.name,
            "Case variable has illegal name self"
    );
    return null;
   }
   if(SymbolTable.globals.lookup(caseOption.type.getText()) == null)
   {
    SymbolTable.error(context,
            caseOption.type,
            "Case variable " + caseOption.name.getText()  + " has undefined type " + caseOption.type.getText()
    );
    return null;
   }
   if(caseOption.type.getText().compareTo("SELF_TYPE") == 0)
  {
   SymbolTable.error(context,
           caseOption.type,
           "Case variable " + caseOption.name.getText()  + " has illegal type SELF_TYPE"
   );
   return null;
  }

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
  //System.out.println(str);
 }

}
