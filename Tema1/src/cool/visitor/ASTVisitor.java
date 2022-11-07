package cool.visitor;

import cool.ast.*;

public interface ASTVisitor<T> {
    T visit(ASTclassDef asTclassDef);

    T visit(Id id);

    T visit(Prog prog);

    T visit(Instruction instruction);

    T visit(Formal formal);
    T visit(ASTclassMemberDef varDef);
    T visit(ASTmethodDef funcDef);

    T visit(ASTclassNode asTclassNode);
    T visit(Assign2 assign2);
    T visit(Int iint);
    T visit(ASTString str);
    T visit(Bool bol);

}
