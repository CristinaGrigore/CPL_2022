package cool.visitor;

import cool.ast.*;
import cool.structures.*;

import java.util.ArrayList;
import java.util.HashSet;

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
  var context = id.getContext();
  var symbol = (IdSymbol)scope.lookup(id.getToken().getText());
  if(id.getToken().getText().compareTo("self") == 0)
   return TypeSymbol.SELF_TYPE;
  if(symbol == null)
  {
   SymbolTable.error(context,
           id.getToken(),
           "Undefined identifier " + id.getToken().getText());
   return null;
  }
  return symbol.getType();
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
  var id = formal.name.getText();
  var formalType = formal.type.getText();
  var methodScope = (MethodSymbol)scope;
  var classScope = (TypeSymbol)scope.getParent();

  if(SymbolTable.globals.lookup(formalType) == null)
  {
   SymbolTable.error(
           formal.getContext(),
           formal.type,
           "Method " + methodScope.getName() + " of class "  + classScope.getName()
                   + " has formal parameter " + id + " with undefined type " + formalType
   );
   return null;
  }

  return null;
 }

 @Override
 public TypeSymbol visit(ASTclassMemberDef varDef) {
  var symbol = varDef.getSymbol();
  var context = varDef.getContext();

  if (symbol == null) {
   return null;
  }
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
  var attribType = varDef.type.getText();
  var typeSymbol = SymbolTable.globals.lookup(attribType);
  if(typeSymbol == null) {
   SymbolTable.error(context,
           varDef.type,
           "Class " + ((TypeSymbol)scope).getName() + " has attribute " + varDef.name.getText() + " with undefined type " + attribType
   );
   varDef.setSymbol(null);
   return null;
  }
  if(varDef.init != null)
   varDef.init.accept(this);
  varDef.getSymbol().setType((TypeSymbol) typeSymbol);
  return (TypeSymbol) typeSymbol;
 }

 @Override
 public TypeSymbol visit(ASTmethodDef funcDef) {
  var id = funcDef.name;
  var symbol = funcDef.methodSymbol;
  if(symbol == null)
   return null;
  var context = funcDef.getContext();
  var parentScope = scope.getParent();

  var returnType = funcDef.returnType;
  var retType = SymbolTable.globals.lookup(returnType.getText());
  if(SymbolTable.globals.lookup(returnType.getText()) == null)
  {
   SymbolTable.error(context,
           funcDef.returnType,
           "Class " + ((TypeSymbol)scope).getName() + " has method " + id.getText() + " with undefined return type " + returnType.getText()
   );
   return null;
  }
  symbol.setReturnType((TypeSymbol) retType);
  var nrParams = funcDef.params.size();
  var overriddenMethod = ((TypeSymbol)parentScope).lookupMethod(id.getText());
  var parentMethod = (parentScope).lookup(id.getText());

  if(overriddenMethod != null)
  {
    //method is overridden
   var params = funcDef.methodSymbol.getParams();
   var overriddenParams = overriddenMethod.getParams();
    var nrOverridenParams = overriddenParams.size();
    //methos is overriden with different nr of parameters
    if(nrParams != nrOverridenParams)
    {
     SymbolTable.error(context,
             id,
             "Class "+ ((TypeSymbol)scope).getName() + " overrides method " +
             id.getText() + " with different number of formal parameters"
     );
     return null;
    }

    var overriddenReturnType = overriddenMethod.getReturnType().getName();
    if(retType.getName().compareTo(overriddenReturnType) != 0)
    {
     SymbolTable.error(context,
             funcDef.returnType,
             "Class "+ ((TypeSymbol)scope).getName() + " overrides method " +
                     id.getText() + " but changes return type from " + overriddenReturnType +
                     " to " + retType.getName()
     );
     return null;
    }
    //method is overriden and type of parameter is changed
   for(int i = 0; i < nrParams; i++)
   {
     var type = params.get(i).getType();
     var overriddenType = overriddenParams.get(i).getType();
     if(type.getName().compareTo(overriddenType.getName()) != 0) {

      SymbolTable.error(context,
              funcDef.params.get(i).type,
              "Class " + ((TypeSymbol) scope).getName() + " overrides method " + funcDef.name.getText()
                      + " but changes type of formal parameter " + params.get(i).getName() + " from "
                      + overriddenType.getName() + " to " + type.getName()
      );
      return null;
     }

   }

  }
  scope = symbol;
  var body = funcDef.body;
  body.forEach(expr -> expr.accept(this));
  funcDef.params.forEach(node -> node.accept(this));
  scope = symbol.getParent();
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
  if(assign2.name.getToken().getText().compareTo("self") == 0)
   SymbolTable.error(assign2.getContext(),
           assign2.name.getToken(),
           "Cannot assign to self");
  var idSymbol = (IdSymbol)scope.lookup(assign2.name.getToken().getText());
  if (idSymbol == null) {
   return null;
  }
  var type = (TypeSymbol)SymbolTable.globals.lookup(idSymbol.getType().getName());
  var exprType = assign2.e.accept(this);

 if(type == null || exprType == null)
  return null;

 if(type == TypeSymbol.SELF_TYPE)
 {
  SymbolTable.error(assign2.getContext(),
          assign2.name.getToken(),
          "Cannot assign to self"
  );
 }
 if(!exprType.isCompatible(type))
 {
  SymbolTable.error(assign2.getContext(),
          assign2.e.getToken(),
          "Type " + exprType.getName() + " of assigned expression is incompatible with declared type " +
          type.getName() + " of identifier " + assign2.name.getToken().getText());
 }



 return type;
 }

 @Override
 public TypeSymbol visit(Int iint) {
  return TypeSymbol.INT;
 }

 @Override
 public TypeSymbol visit(ASTString str) {
  return TypeSymbol.STRING;
 }

 @Override
 public TypeSymbol visit(Bool bol) {
  return TypeSymbol.BOOL;
 }

 @Override
 public TypeSymbol visit(Plus plus) {
  var context = plus.getContext();
  var left = plus.left.accept(this);
  var right = plus.right.accept(this);
 if(left == null || right == null)
  return null;
  if(left.getName().compareTo("Int") != 0)
  {
   SymbolTable.error(context,
           plus.left.getToken(),
           "Operand of + has type " + left.getName() +
                   " instead of Int"
   );
  }
  if(right.getName().compareTo("Int") != 0)
  {
   SymbolTable.error(context,
           plus.right.getToken(),
           "Operand of + has type " + right.getName() +
                   " instead of Int"
   );
  }
  return TypeSymbol.INT;
 }

 @Override
 public TypeSymbol visit(Minus minus) {

  var context = minus.getContext();
  var left = minus.left.accept(this);
  var right = minus.right.accept(this);

  if(right != TypeSymbol.INT)
  {
   SymbolTable.error(context,
           minus.right.getToken(),
           "Operand of - has type " + right.getName() +
                   " instead of Int"
   );
 //  return right;
  }
  if(left != TypeSymbol.INT )
  {
   SymbolTable.error(context,
           minus.left.getToken(),
           "Operand of - has type " + left.getName() +
           " instead of Int"
   );
  // return left;
  }

  return TypeSymbol.INT;
 }

 @Override
 public TypeSymbol visit(Mult mult) {

  var context = mult.getContext();
  var left = mult.left.accept(this);
  var right = mult.right.accept(this);

  if(left != TypeSymbol.INT)
  {
   SymbolTable.error(context,
           mult.getToken(),
           "Operand of * has type " + left.getName() +
                   " instead of Int"
   );
  }
  if(right != TypeSymbol.INT)
  {
   SymbolTable.error(context,
           mult.right.getToken(),
           "Operand of * has type " + right.getName() +
                   " instead of Int"
   );
  }

  return TypeSymbol.INT;
 }

 @Override
 public TypeSymbol visit(Div div) {

  var context = div.getContext();
  var left = div.left.accept(this);
  var right = div.right.accept(this);
  if(left != TypeSymbol.INT)
  {
   SymbolTable.error(context,
           div.getToken(),
           "Operand of / has type " + left.getName() +
                   " instead of Int"
   );
  }
  if(right != TypeSymbol.INT)
  {
   SymbolTable.error(context,
           div.getToken(),
           "Operand of / has type " + right.getName() +
                   " instead of Int"
   );
  }

  return TypeSymbol.INT;
 }

 @Override
 public TypeSymbol visit(Paren paren) {
  var exprType = paren.e.accept(this);
  return exprType;
 }

 @Override
 public TypeSymbol visit(Negation negation) {
  var context = negation.getContext();
  var expr = negation.e.accept(this);

  if(expr != TypeSymbol.INT)
  {

   SymbolTable.error(context,
           negation.e.getToken(),
           "Operand of ~ has type " + expr.getName() +
                   " instead of Int"
   );

  }
  return TypeSymbol.INT;
 }

 @Override
 public TypeSymbol visit(ASTRelational relational) {

 var context = relational.getContext();
 var left = relational.left.accept(this);
 var right = relational.right.accept(this);
 if(left == null || right == null)
  return null;
  if(relational.value.getText().compareTo("=") == 0)
  {
   if(!areComparable(left, right))
   {
    SymbolTable.error(context,
            relational.value,
            "Cannot compare " + left.getName() + " with " + right.getName()
    );
   }
  }
  else if(left.getName().compareTo("Int") != 0)
  {
   SymbolTable.error(context,
           relational.left.getToken(),
           "Operand of " + relational.value.getText() + " has type " + left.getName() +
                   " instead of Int"
   );
  }
  else if(right.getName().compareTo("Int") != 0)
  {
   SymbolTable.error(context,
           relational.right.getToken(),
           "Operand of "  + relational.value.getText() + " has type " + right.getName() +
                   " instead of Int"
   );

  }

  return TypeSymbol.BOOL;
 }

 private boolean areComparable(TypeSymbol left, TypeSymbol right) {
  if(left == TypeSymbol.INT || left == TypeSymbol.STRING || left == TypeSymbol.BOOL)
  {
    if(right != left)
     return false;
  }
  if(right == TypeSymbol.INT || right == TypeSymbol.STRING || right == TypeSymbol.BOOL)
  {
   if(right != left)
    return false;
  }
  return true;
 }

 @Override
 public TypeSymbol visit(Assign assign) {
  return null;
 }

 @Override
 public TypeSymbol visit(Not not) {
  var context = not.getContext();
  var expr = not.e.accept(this);
  if(expr == null)
    return null;
  if(expr != TypeSymbol.BOOL)
  {
   SymbolTable.error(context,
           not.e.getToken(),
           "Operand of not has type " + expr.getName() +
                   " instead of Bool"
   );
   return null;
  }
  return TypeSymbol.BOOL;
 }

 @Override
 public TypeSymbol visit(IsVoid isVoid) {
  return TypeSymbol.BOOL;
 }

 @Override
 public TypeSymbol visit(New aNew) {
  var type = aNew.e.getText();
  var typeSymbol = SymbolTable.globals.lookup(type);
  if(typeSymbol == null)
  {
   SymbolTable.error(aNew.getContext(),
           aNew.e,
           "new is used with undefined type " + type);
   return null;
  }
  return (TypeSymbol) typeSymbol;
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
  var context = anIf.getContext();
  var type = anIf.cond.accept(this);
  if(type != TypeSymbol.BOOL)
  {
   SymbolTable.error(context,
           anIf.cond.getToken(),
           "If condition has type " + type.getName() +
                   " instead of Bool"
   );
   return null;
  }
  var typeThen = anIf.thenBranch.accept(this);
  var typeElse = anIf.elseBranch.accept(this);
  var ancestors = new HashSet<TypeSymbol>();
  while(typeThen != null)
  {
   ancestors.add(typeThen);
   typeThen = (TypeSymbol) typeThen.getParent();
  }
  while(typeElse != null)
  {
   if(ancestors.contains(typeElse))
    return typeElse;
   typeElse = (TypeSymbol) typeElse.getParent();
  }
  return TypeSymbol.OBJECT;
 }

 @Override
 public TypeSymbol visit(While aWhile) {
  var context = aWhile.getContext();
  var type = aWhile.cond.accept(this);
  if (type != TypeSymbol.BOOL) {
   SymbolTable.error(
          context,
           aWhile.cond.getToken(),
           "While condition has type " + type.getName() + " instead of Bool"
   );

  }

  return TypeSymbol.OBJECT;
 }

 @Override
 public TypeSymbol visit(VarDef varDef) {
  var id = varDef.id.getText();
  var idSymbol = (IdSymbol)scope.lookup(id);

  var value = varDef.expr;
  if (value != null)
   value.accept(this);
  return null;
 }

 @Override
 public TypeSymbol visit(Let let) {
  var context = let.getContext();
  var retValue = let.body.accept(this);
  if(let.members != null)
   let.members.forEach(member -> member.accept(this));
  return null;
 }

 @Override
 public TypeSymbol visit(CaseOption caseOption) {
 // var retType = caseOption.value.accept(this);
 // return retType;
  return null;
 }

 @Override
 public TypeSymbol visit(Case aCase) {
 // System.out.println("case");
 // aCase.name.accept(this);
//  aCase.options.forEach(opt -> opt.accept(this));
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
