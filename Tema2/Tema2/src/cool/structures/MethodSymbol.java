package cool.structures;

import java.util.Collection;
import java.util.LinkedHashMap;

public class MethodSymbol extends Symbol implements Scope {
 protected TypeSymbol parent;
 protected final LinkedHashMap<String, IdSymbol> params = new LinkedHashMap<>();

 public MethodSymbol(String name) {
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

 public LinkedHashMap<String, IdSymbol> getParams() {
  return params;
 }
}
