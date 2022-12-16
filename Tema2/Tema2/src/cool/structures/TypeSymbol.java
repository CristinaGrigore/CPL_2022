package cool.structures;

public class TypeSymbol extends Symbol implements Scope {
  protected Scope parent;
 public static final TypeSymbol OBJECT = new TypeSymbol("Object", null);
 public static final TypeSymbol INT = new TypeSymbol("Int", "Object");
 public static final TypeSymbol BOOL = new TypeSymbol("Bool", "Object");
 public static final TypeSymbol STRING = new TypeSymbol("String", "Object");
 public static final TypeSymbol IO = new TypeSymbol("IO", "Object");
 public static final TypeSymbol SELF_TYPE = new TypeSymbol("SELF_TYPE", "Object");

 public TypeSymbol(String name, String parentName) {
  super(name);

 }

 @Override
 public boolean add(Symbol sym) {
  return false;
 }

 @Override
 public Symbol lookup(String str) {
  return null;
 }

 @Override
 public Scope getParent() {
  return null;
 }
}
