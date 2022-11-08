package cool.visitor;

import cool.ast.*;

public interface ASTVisitor<T> {
    T visit(ASTclassDef asTclassDef);

    T visit(Id id);

    T visit(Prog prog);

  //  T visit(Instruction instruction);

    T visit(Formal formal);
    T visit(ASTclassMemberDef varDef);
    T visit(ASTmethodDef funcDef);

    T visit(ASTclassNode asTclassNode);
    T visit(Assign2 assign2);
    T visit(Int iint);
    T visit(ASTString str);
    T visit(Bool bol);
    T visit(Plus plus);
    T visit(Minus minus);

    T visit(Mult mult);

    T visit(Div div);

    T visit(Paren paren);

    T visit(Negation negation);


    T visit(ASTRelational relational);

    T visit(Assign assign);

    T visit(Not not);

    T visit(IsVoid isVoid);

    T visit(New aNew);

    T visit(ImplicitDispatch implicitDispatch);

    T visit(Dispatch dispatch);

    T visit(If anIf);
}
