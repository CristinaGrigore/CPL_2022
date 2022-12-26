package cool.structures;

import cool.ast.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

public class MethodSymbol extends Symbol implements Scope {
 protected Scope parent;
 protected LinkedHashMap<String, IdSymbol> params = new LinkedHashMap<>();
protected TypeSymbol type;
protected  TypeSymbol returnType;
 public MethodSymbol(String name)
 {
  super(name);
 }

 @Override
 public boolean add(Symbol sym) {
  if (!(sym instanceof IdSymbol)) {
   return false;
  }
  String symbolName = sym.getName();
  if (params.containsKey(symbolName)) {
   return false;
  }

  params.put(symbolName, (IdSymbol)sym);
  return true;
 }

 @Override
 public Symbol lookup(String str) {
  IdSymbol symbol = params.get(str);

  if (symbol != null) {
   return symbol;
  }
  return parent.lookup(str);
 }

 @Override
 public Scope getParent() {
  return parent;
 }

 public ArrayList<IdSymbol> getParams() {
  return new ArrayList<IdSymbol>(params.values());
 }
public void setType(TypeSymbol type) {
  this.type = type;
}
 public TypeSymbol getType() {
  return type;
 }

 public void setParent(Scope parent) {
  this.parent = parent;
 }
 public void setReturnType(TypeSymbol type) {
  this.returnType = type;
 }
 public TypeSymbol getReturnType() {
  return returnType;
 }
}
