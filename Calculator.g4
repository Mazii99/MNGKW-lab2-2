grammar Calculator;

expr:
	multdivexpr ((PLUS | MINUS) multdivexpr)* #expression
	;
multdivexpr: 
	powexpr ((TIMES | DIV) powexpr)* #multdivexpression
	;
powexpr:
	INT ((POW | SQRT) INT)* # powerexpression
	;		
	
INT: [0-9]+ ;
DOT: '.';
TIMES: '*' ;
DIV: '/' ;
PLUS: '+' ;
MINUS: '-' ;
POW: 'pow';
SQRT: 'sqrt';
LGROUP: '(';
RGROUP: ')';
WS : [ \t\r\n]+ -> skip ;
