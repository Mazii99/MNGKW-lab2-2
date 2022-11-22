grammar Calculator;

expr:
	multdivexpr ((PLUS | MINUS) multdivexpr)* #expression
	;
multdivexpr: 
	powexpr ((TIMES | DIV) powexpr)* #multdivexpression
	;
powexpr:
	logexpr ((POW | SQRT) logexpr)* # powerexpression
	;
logexpr:
    LOG? intexpr* #logexpression
    ;
intexpr:
    (MINUS INT| INT) # intexpression
    ;
	
INT: [0-9]+ ;
DOT: '.';
TIMES: '*' ;
DIV: '/' ;
PLUS: '+' ;
MINUS: '-' ;
POW: 'pow';
SQRT: 'sqrt';
LOG: 'log';
LGROUP: '(';
RGROUP: ')';
WS : [ \t\r\n]+ -> skip ;
