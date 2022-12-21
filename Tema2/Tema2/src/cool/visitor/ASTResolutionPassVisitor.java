package cool.visitor;

import cool.ast.*;
import cool.structures.*;

import java.util.ArrayList;

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
  for (var stmt: prog.class_definitions) {
   stmt.accept(this);
  }
  return null;
 }

 @Override
 public TypeSymbol visit(Formal formal) {
  return null;
 }

 @Override
 public TypeSymbol visit(ASTclassMemberDef varDef) {
  var symbol = varDef.getSymbol();
  var context = varDef.getContext();

  var parentScope = scope.getParent();

  if (parentScope.lookup(varDef.name.getText()) != null) {
   SymbolTable.error(
           context,
           varDef.name,
           "Class " + ((TypeSymbol)scope).getName()
                   + " redefines inherited attribute " + varDef.name.getText()
   );
   varDef.setSymbol(null);

   return null;
  }
  return null;
 }

 @Override
 public TypeSymbol visit(ASTmethodDef funcDef) {
  var id = funcDef.name;
  var context = funcDef.getContext();
  var parentScope = scope.getParent();

  var returnType = funcDef.returnType;
  if(SymbolTable.globals.lookup(returnType.getText()) == null)
  {
   SymbolTable.error(context,
           funcDef.returnType,
           "Class " + ((TypeSymbol)scope).getName() + " has method " + id.getText() + " with undefined return type " + returnType.getText()
   );
   return null;
  }
  var nrParams = funcDef.params.size();
  var overriddenMethod = (MethodSymbol)((TypeSymbol)parentScope).lookup(id.getText());
  if(overriddenMethod != null)
  {
    //method is overridden
    var nrOverridenParams = overriddenMethod.getParams().size();
    //methos is overriden with different nr of parameters
    if(nrParams == nrOverridenParams)
    {
     SymbolTable.error(context,
             id,
             "Class "+ ((TypeSymbol)scope).getName() + " overrides method" +
             id.getText() + " with different number of formal parameters"
     );
     return null;
    }
    //method is overriden and type of parameter is changed
   int i = 0;
    for(var p : overriddenMethod.getParams().values())
    {
     var p1 = funcDef.params.get(i++);
     System.out.println("Class "+ ((TypeSymbol)scope).getName() + "param " + id.getText() +
             "orig type " + p1.type.getText() + "overr " + p.getType().getName());
     if(p.getType() != p1.type)
      SymbolTable.error(context,
              id,
              "Class "+ ((TypeSymbol)scope).getName() + " overrides method" +
                      id.getText() + " but changes type of formal parameter " +
                      p.getName() + " from " + p1.type + " to " + p.getType()
      );
     return null;
    }
  }

  return null;
 }

 @Override
 public TypeSymbol visit(ASTclassNode asTclassNode) {
  if(asTclassNode.type == null)
   return null;

  scope = SymbolTable.globals;
  var context = asTclassNode.getContext();

  if(!(asTclassNode.name2 == null) && scope.lookup(asTclassNode.name2.getText()) == null)
  {
   SymbolTable.error(context,
                     asTclassNode.name2,
                     "Class " + asTclassNode.name.getText() + " has undefined parent " + asTclassNode.name2.getText());
   return null;
  }
  var currentType = asTclassNode.getType();

  Symbol parentType;
  //iterez prin ierarhia de mostenire
  while(currentType != TypeSymbol.OBJECT) {

    parentType = SymbolTable.globals.lookup(currentType.getParentName());
   if(parentType == null)
   {
    SymbolTable.error(
            asTclassNode.getContext(),
            asTclassNode.name,
            "Class " + asTclassNode.name.getText() + " has undefined parent " + currentType.getParentName()
    );
    return null;
   }
   currentType.setParent((TypeSymbol)parentType);
   currentType = (TypeSymbol)currentType.getParent();


   if (currentType == asTclassNode.getType()) {
    SymbolTable.error(
            asTclassNode.getContext(),
            asTclassNode.name,
            "Inheritance cycle for class " + asTclassNode.name.getText()
    );
    return null;
   }
  }

  scope = asTclassNode.getType();
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
