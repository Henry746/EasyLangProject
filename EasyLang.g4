grammar EasyLang;

prog:	(expr NEWLINE)* ;
expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;

/*assign : id = expr ;
id     : 'A' | 'B' | 'C' ;
expr   : id '+' expr
       | id '*' expr
       | '(' expr ')'
       | id ;*/