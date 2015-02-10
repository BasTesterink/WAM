grammar Prolog;

// Comments
LINECOMM : ('//'|'%') (~('\n'|'\r'))* -> channel(HIDDEN);
MULTLINECOMM : '/*' (('*' ~'/')|(~'*'))* '*/' -> channel(HIDDEN);
WS : [ \t\r\n]+ -> channel(HIDDEN); 

// Delimiters
LBRACKET : '[';     RBRACKET : ']';     LPAREN : '(';
RPAREN : ')';       LBRACE : '{';       RBRACE : '}'; 
COMMA : ',';        BAR : '|';          DOT : '.';            
                                        
// Literals
CHARARRAY : '"' (~('\n'|'\r'|'"'))* '"'; 
INT : [0-9]+;
FLOAT : [0-9]+ '.' [0-9]+;

// Identifiers
CON : ('\'' (~('\n'|'\r'|'\''))* '\'')|([a-z]+ ([a-z]|[A-Z]|[0-9]|'_')*)|'!';
VAR : ([A-Z]|'_')+ ([a-z]|[A-Z]|[0-9]|'_')*;
predname : CON|(';'|'-'|'>'|'*'|':'|'='|'\\'|'+'|'<'|'@'|'/'|'?'|'^'|'$'|' is '
     |' as '|' xor '|' div '|' rdiv '|' mod '|' rem ' | BAR | DOT)+;

	 
// Argument  
argseq : expr (COMMA expr)*;
parenthesized : LPAREN argseq RPAREN;
expr:	
    	(parenthesized | list | predicate | number | variable | CHARARRAY) 
	|	expr ('-->'|':-') expr
	|	('?-') expr
	| 	expr (';') expr
	| 	expr ('->'|'*->') expr
	| 	expr ':=' expr
	| 	('\\+'|'not ') expr
	| 	expr ('<'|'=@='|'\\=@='|'=:='|'=<'|'=='|'=\\='|'>'|'>='|'@<'|'@=<'|'@>'|'@>='|'\\='|'\\=='|'>:<'|':<') expr
	| 	expr ':' expr 
	| 	expr ('*'|'/'|'//'|' div '|' rdiv '|'<<'|'>>'|' mod '|' rem ') expr
	| 	expr ('+'|'-'|'/\\'|'\\/'|' xor ') expr
	|	expr ('?') expr
	|	expr ('**') expr
	|	expr ('^') expr
	|	expr (' is '|'='|'=..') expr
	|	('$') expr
    ;

// Datastructures
list : LBRACKET (argseq (BAR (list | variable))?)? RBRACKET;
predicate : (predname parenthesized?) | parenthesized ; 
plrule : predicate (':-' argseq)? '.';
query : '?-' argseq '.';
number : '-'? (INT|FLOAT);
variable : VAR;

toprule : (plrule|query)*;