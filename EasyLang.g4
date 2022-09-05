/*
 * Easy Language Grammar
 *
 * Date Created: July 16, 2018
 * Created by: Engr. Jomin N. Yu
 *
 * Copyleft (c) 2018 Jomin Yu
 */
grammar EasyLang;

program    : 'program' ID? statements function* ;

function   : 'function' ID '('(ID (',' ID)*)?')' statements ;

method     : access 'static'? (type|'void') ID
             '(' (type ID ('[' ']')* (',' type ID ('[' ']')*)* )? ')'
             statements;

access     : 'private' | 'public' | 'protected';

statements : '{' statement* '}' ;

statement  :
    assign                                              #ASSIGN_STMT
    | varDec                                            #VARDEC_STMT
    | 'if' expr statements ('else' statements)?         #IF_STMT
    | 'switch' expr '{' 
        ('case' expr (',' expr)* ':' statements)+
        ('default' ':' statements)? '}'                 #SWITCH_STMT
    | 'for' assign 'to' expr ('step' expr)? statements  #FOR_STMT
    | 'while' expr statements                           #WHILE_STMT
    | 'do' statements 'while' expr                      #DOWHILE_STMT
    | 'input' ID (',' ID)*                              #READ_STMT
    | 'output' expr (',' expr)*                         #WRITE_STMT
    | ID '(' (expr (',' expr)*)? ')'                    #FUNC_STMT
    | 'return' expr                                     #RET_STMT
    ;

assign     :
    ID ('[' expr ']')* ('='|'+='|'-='|'*='|'/='|'%=') expr ;

varDec:
        type ('[' ']')* ID ( '=' expr)?;

type:
        'int'                                           #INT_TYPE
        | 'string'                                      #STRING_TYPE
        | 'bool'                                        #BOOL_TYPE
        | 'float'                                       #FLOAT_TYPE
        ;

expr    :	('+'|'-') expr                              #SIGN_EXPR
        |	expr ('*'|'/'|'%') expr                     #MULT_EXPR
        |	expr ('+'|'-') expr                         #ADD_EXPR
        |   expr ('>' | '<' | '>=' | '<=' ) expr        #REL_EXPR
        |   expr ('=' | '!=') expr                      #EQ_EXPR
        |   expr '&&' expr                              #AND_EXPR
        |   expr '||' expr                              #OR_EXPR
        |   expr '?' expr ':' expr                      #TERNARY_EXPR
        |	INT                                         #INT_EXPR
        |   HEXA                                        #HEXA_EXPR
        |	FLOAT                                       #FLOAT_EXPR
        |   BOOLEAN                                     #BOOLEAN_EXPR
        |   ID ('[' expr ']')*                          #ID_EXPR
        |   ID '(' (expr (',' expr)*)? ')'              #FUNC_EXPR
        |	DATE                                        #DATE_EXPR
        |	CHAR                                        #CHAR_EXPR
        |	STRING                                      #STRING_EXPR
        |	'(' expr ')'                                #EXPR_EXPR
        |   '{' (expr (',' expr)*)? '}'                 #ARRAY_EXPR
        ;

//rule  : 'hello' (ID | INT | HEXA | FLOAT | CHAR | STRING | DATE)+ ;

BOOLEAN : 'true' | 'false' ;

ID : [a-zA-Z_][a-zA-Z_0-9]* ;

INT : DIGIT+ ;

HEXA : '0x' HEX HEX? HEX? HEX? HEX? HEX? HEX? HEX? ;

FLOAT : (DIGIT+[.]DIGIT*|[.]DIGIT+)([eE][-+]?DIGIT+)? ;

DATE :  ('0'?[1-9] | '1'[0-2]) '.' ('0'?[1-9] | [1-2]DIGIT | '3'[0-1]) '.' DIGIT+ ;

CHAR : '\'' (~['\r\n\t\\] | '\\' ['rnt\\]) '\'' ;

STRING : '"' (~["\r\n\t\\] | '\\' ["rnt\\])* '"' ;

// TELNO: # (area code) {with or without ()} - (dash) 7 to 8 digits
// DOW: day or week, Monday, Tue, ...
// PRODCODE: Product code: #XX-9999

fragment DIGIT : [0-9] ;

fragment HEX : [0-9a-fA-F] ;

WS : [ \t\r\n]+ -> skip ;       // skip spaces, tabs, newlines

COMMENT :      '/*' .*? '*/' -> skip ;

LINE_COMMENT : '//' ~[\r\n]* -> skip ;