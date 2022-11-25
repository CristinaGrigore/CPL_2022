lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

/* Reguli de funcționare:
 *
 * * Se ia în considerare cel mai lung lexem recunoscut, indiferent de ordinea
 *   regulilor din specificație (maximal munch).
 *
 * * Dacă există mai multe cele mai lungi lexeme, se ia în considerare prima
 *   regulă din specificație.
 */

/* Cuvânt cheie.
 */
IF : 'if';
THEN : 'then';
ELSE : 'else';
FI: 'fi';
WHILE : 'while';
LOOP : 'loop';
POOL : 'pool';
BOOL : 'true' | 'false';

TYPE : (UPPERCASE | '_')(LETTER | '_' | DIGIT)*;
CLASS : 'class';
INHERITS: 'inherits';
NOT : 'not';
ISVOID : 'isvoid';
NEW : 'new';
LET : 'let';
IN : 'in';
CASE : 'case';
ESAC : 'esac';
OF : 'of';

/* Identificator.
 */
fragment LETTER : [a-zA-Z];
fragment UPPERCASE : [A-Z];
fragment LOWERCASE : [a-z];
ID : (LOWERCASE | '_')(LETTER | '_' | DIGIT)*;
/* Număr întreg.
 *
 * fragment spune că acea categorie este utilizată doar în interiorul
 * analizorului lexical, nefiind trimisă mai departe analizorului sintactic.
 */
fragment DIGIT : [0-9];
INT : DIGIT+;

/* Număr real.
 */
fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;

/* Șir de caractere.
 *
 * Poate conține caracterul '"', doar precedat de backslash.
 * . reprezintă orice caracter în afară de EOF.
 * *? este operatorul non-greedy, care încarcă să consume caractere cât timp
 * nu a fost întâlnit caracterul ulterior, '"'.
 *
 * Acoladele de la final pot conține secvențe arbitrare de cod Java,
 * care vor fi executate la întâlnirea acestui token.
 */
 fragment ANY_LETTER_EXCEPT : [^ntrb];
STRING: '"' ('\\"' | '\\' NEW_LINE | .)*? (
	'"' {
		String str = getText();
		str = str
			.substring(1, str.length() - 1)
			.replace("\\n", "\n")
			.replace("\\t", "\t")
			.replace("\\b", "\b")
		.replaceAll("\\\\(?!\\\\)", "");
		if (str.length() > 1024) {
			raiseError("String constant too long");
		} else if (str.contains("\0")) {
			raiseError("String contains null character");
		} else {
			setText(str);
		}
	}
	| EOF { raiseError("EOF in string constant"); }
	| NEW_LINE { raiseError("Unterminated string constant"); }
);

SEMI : ';';

COMMA : ',';

ASSIGN : '=';

LPAREN : '(';

RPAREN : ')';

LBRACE : '{';

RBRACE : '}';

PLUS : '+';

MINUS : '-';

MULT : '*';

DIV : '/';

EQUAL : '==';

LT : '<';

LE : '<=';

COLON : ':';

ASSIGN2 : '<-';

NEG: '~';

DISPATCH : '.';

AT : '@';

RESULT : '=>';

fragment NEW_LINE : '\r'? '\n';

LINE_COMMENT
    : '--' .*? (NEW_LINE | EOF) -> skip
    ;

BLOCK_COMMENT
    : '(*'
      (BLOCK_COMMENT | .)*? { skip(); }
      ('*)' | EOF { raiseError("EOF in comment"); })
    ;
UNFINISHED_COMMENT: '*)' { raiseError("Unmatched *)"); };
/* Spații albe.
 *
 * skip spune că nu este creat niciun token pentru lexemul depistat.
 */
WS : [ \n\r\t]+ -> skip;
UNKNOWN: . { raiseError("Invalid character: " + getText()); };

/* Modalitate alternativă de recunoaștere a șirurilor de caractere, folosind
 * moduri lexicale.
 *
 * Un mod lexical, precum cel implicit (DEFAULT_MODE) sau IN_STR, de mai jos,
 * reprezintă stări ale analizorului. Când analizorul se află într-un anumit
 * mod, numai regulile din acel mod se pot activa.
 *
 * Folosim pushMode și popMode pentru intra și ieși din modurile lexicale,
 * în regim de stivă.
 *
 * more spune că deocamdată nu este construit un token, dar lexemul identificat
 * va face parte, cumulativ, din lexemul recunoscut de următoarea regulă.
 *
 * De-abia la recunoașterea caracterului '"' de sfârșit de șir de către regula
 * STR, se va construi un token cu categoria STR și întregul conținut al șirului
 * drept lexem.
 */
/*
STR_OPEN : '"' -> pushMode(IN_STR), more;

mode IN_STR;

STR : '"' -> popMode;
CHAR : ('\\"' | ~'"') -> more;  // ~ = complement
*/