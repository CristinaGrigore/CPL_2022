package cool.structures;

import cool.ast.ASTmethodDef;
import cool.ast.Type;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeSymbol extends Symbol implements Scope {
  protected TypeSymbol parent;
  protected String parentName;
 private Map<String, MethodSymbol> methods = new LinkedHashMap<>();
 private Map<String, Symbol> symbols = new LinkedHashMap<>();
 public static final TypeSymbol OBJECT = new TypeSymbol("Object", null);
 public static final TypeSymbol INT = new TypeSymbol("Int", "Object");
 public static final TypeSymbol BOOL = new TypeSymbol("Bool", "Object");
 public static final TypeSymbol STRING = new TypeSymbol("String", "Object");
 public static final TypeSymbol IO = new TypeSymbol("IO", "Object");
 public static final TypeSymbol SELF_TYPE = new TypeSymbol("SELF_TYPE", "Object");

 public TypeSymbol(String name, String parentName) {
  super(name);
  var symbol = new IdSymbol("self");
  symbol.setType(SELF_TYPE);
  this.parentName = parentName;
 }

 @Override
 public boolean add(Symbol sym) {
  // Reject duplicates in the same scope.
  if (symbols.containsKey(sym.getName()))
   return false;

  symbols.put(sym.getName(), sym);
  return true;
 }

 @Override
 public Symbol lookup(String str) {
  var sym = symbols.get(str);

  if (sym != null)
   return sym;
  if (parent != null)
   return parent.lookup(str);

  return null;
 }

 @Override
 public Scope getParent() {
  return parent;
 }
 public void setParent(TypeSymbol parent) {
  this.parent = parent;
 }
 public String getParentName() {
  return parentName;
 }

 public boolean addMethod(MethodSymbol symbol) {
  String symbolName = symbol.getName();
  if (methods.get(symbolName) != null) {
   return false;
  }

  methods.put(symbolName, symbol);

  return true;
 }

 public MethodSymbol lookupMethod(String name) {
  var sym = methods.get(name);

  if (sym != null)
   return sym;
  if (parent != null)
   return ((TypeSymbol)parent).lookupMethod(name);
  return null;
 }

 public Collection<Symbol> getSymbols() {
  return symbols.values();
 }

 //recursive method to determine if types of assigned variables are compatible
 //(they have the same type or one inherits the other)
 public boolean isCompatible(TypeSymbol type) {
  if (this == type) {
   return true;
  }
  if (parent != null) {
   return parent.isCompatible(type);
  }

  return false;
 }
}
