package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends IdSymbol implements Scope {
 protected Map<String, Symbol> symbols = new LinkedHashMap<>();
 protected Scope parent;

 public FunctionSymbol(String name, Scope parent) {
  super(name);
  this.parent = parent;
 }

 @Override
 public boolean add(Symbol s) {
  if (symbols.containsKey(s.getName()))
   return false;

  symbols.put(s.getName(), s);
  return true;
 }

 @Override
 public Symbol lookup(String s) {
  var sym = symbols.get(s);

  if (sym != null)
   return sym;

  if (parent != null)
   return parent.lookup(s);

  return null;
 }

 @Override
 public Scope getParent() {
  return parent;
 }
}