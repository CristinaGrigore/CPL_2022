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


    caseOptions: name=ID COLON type=TYPE RESULT value=expr SEMI;
    funcCall: name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN;
    expr
        :   name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN     # implicitDispatch
        |   left=expr DISPATCH right=funcCall                               #dispatch
        |   left=expr AT typee=TYPE  DISPATCH rightt=funcCall             #other_dispatch
         |   LPAREN e=expr RPAREN                                        # paren
 | op= NEG  e=expr                                            #neg
           |   STRING                                                      # string
        |   MINUS e=expr                                                # unaryMinus
        |   left=expr op=(MULT | DIV) right=expr                        # multDiv
        |   left=expr op=(PLUS | MINUS) right=expr                      # plusMinus

        |   left=expr op=(LT | LE | EQUAL | ASSIGN) right=expr                  # relational

        |   IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI   # if
        |   WHILE cond=expr LOOP body=expr POOL                         # while
        | LET members+=varDef (COMMA members+=varDef)* IN body=expr	    #let
        | CASE name=expr OF (options+=caseOptions)+ ESAC	               #case
        |   LBRACE (statements+=expr SEMI)+ RBRACE                            #block
        |   name=expr ASSIGN e=expr                                      # assign
        | name=expr (ASSIGN2 e=expr)+                                   #assign2

        |   ID                                                          # id
        |   TYPE                                                        # type
        |   INT                                                         # int
        |   FLOAT                                                       # float
        |   BOOL                                                        # bool


         | op= NOT  e=expr                                            #not
         | ISVOID (e=expr)  #isvoid
         | NEW e=TYPE  #new
        ;
