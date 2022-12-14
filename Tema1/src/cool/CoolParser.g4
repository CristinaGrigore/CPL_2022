parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
        :   (class_definitions+=classDef )* EOF
        ;
   varDef: name=ID COLON type=TYPE (ASSIGN2 value=expr)?;
    definition
        :   name=ID COLON type=TYPE (ASSIGN2 init=expr)? SEMI   # memberDef
        |   name=ID LPAREN (params+=formal (COMMA params+=formal)*)?
          RPAREN COLON returnType=TYPE LBRACE (body+=expr)* RBRACE SEMI	# methodDef
        ;
    classDef: CLASS name=TYPE (INHERITS name2=TYPE)? LBRACE (content+=instruction)* RBRACE SEMI;
    instruction
        :   (definition | expr)
        ;

    formal
        :   name=ID COLON  type=TYPE
        ;

    /* Expresie.
     *
     * În absența numelor din dreapta, precedate de # (if, id și int), în listenerii
     * și visitorii generați automat, ar fi definit doar contextul ExprContext, cu
     * informații amestecate pentru toate cele trei alternative. Însă, în prezența
     * celor trei etichete, care diferențiază alternativele, vor fi generate și
     * cele trei contexte particulare, IfContext, IdContext și IntContext, cu
     * informații specifice fiecărei alternative.
     *
     * Pentru fiecare dintre regulile lexicale sau sintactice referite într-o regulă
     * sintactică, obiectul Context va conține o funcție cu numele regulii. Spre
     * exemplu, obiectul IntContext include o metodă, INT(), care va întoarce
     * nodul aferent din arborele de derivare. Însă, având în vedere că alternativa
     * if conține referiri multiple la regula expr, obiectul IfContext va conține
     * o metodă expr(), care, în loc să întoarcă un singur nod din arbore, va
     * întoarce o listă ordonată cu cele trei noduri menționate, aferente condiției,
     * ramurii THEN și ramurii ELSE. De asemenea, există și o variantă
     * supraîncărcată a metodei, expr(int index), care întoarce nodul de la poziția
     * index, între 0 și 2.
     *
     * În cazul în care referirea la un nod prin poziția sa este insuficient de
     * expresivă, se pot adăuga etichete pentru fiecare referire în parte, ca în
     * cazul cond, thenBranch și elseBranch. În conscință, obiectul IfContext va
     * conține și câmpurile cond, thenBranch și elseBranch, având tipurile nodurilor
     * din arbore.
     */
    caseOptions: name=ID COLON type=TYPE RESULT value=expr SEMI;
    funcCall: name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN;
    expr
        :   name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN     # implicitDispatch
        |   left=expr DISPATCH right=funcCall                               #dispatch
        |   left=expr AT typee=TYPE  DISPATCH rightt=funcCall             #other_dispatch
        |   MINUS e=expr                                                # unaryMinus
        |   left=expr op=(MULT | DIV) right=expr                        # multDiv
        |   left=expr op=(PLUS | MINUS) right=expr                      # plusMinus
        |   left=expr op=(LT | LE | EQUAL) right=expr                  # relational

        |   IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI   # if
        |   WHILE cond=expr LOOP body=expr POOL                         # while
        | LET members+=varDef (COMMA members+=varDef)* IN body=expr	    #let
        | CASE name=expr OF (options+=caseOptions)+ ESAC	               #case
        |   LBRACE (statements+=expr SEMI)+ RBRACE                            #block
        |   name=expr ASSIGN e=expr                                      # assign
        | name=expr (ASSIGN2 e=expr)+                                   #assign2
        |   LPAREN e=expr RPAREN                                        # paren
        |   ID                                                          # id
        |   TYPE                                                        # type
        |   INT                                                         # int
        |   FLOAT                                                       # float
        |   BOOL                                                        # bool
        |   STRING                                                      # string
         | op= NEG  e=expr                                            #neg
         | op= NOT  e=expr                                            #not
         | ISVOID (e=expr)  #isvoid
         | NEW e=TYPE  #new
        ;
