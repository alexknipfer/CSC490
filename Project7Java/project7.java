%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
%}

/* YACC Declarations */
%token ADDOP
%token AND
%token ASSIGNOP
%token COMMA
%token CURLL
%token CURLR
%token ELSE
%token FUNCTION
%token ID
%token IF
%token MULOP
%token NOT
%token NUMBER
%token OR
%token PARENL
%token PARENR
%token RELOP
%token SEMICOLON
%token STRING
%token VAR
%token WHILE

%left ADDOP
%left MULOP


/* Grammar follows */
%%
    start:  pgm
{
    System.out.println("The program is correct, and contains:");
    System.out.printf("%5d statements\n",stmtCount);
    System.out.printf("%5d function definitions\n",funcCount);
}
;

pgm: pgmpart pgm
    | pgmpart
    ;

pgmpart: vardecl
    | function {funcCount++;}
    ;

vardecl: VAR varlist SEMICOLON
    ;

varlist: ID COMMA varlist
      {
        //LinkedList<String> vList = (LinkedList<String>) $3.obj;
        //vList.add($1.sval);
        //$$.obj = vList;
      }
    | ID
    ;

function: FUNCTION ID PARENL PARENR body
            {
                //add label for new funtion
              ICode stmt = new ICode("NOP");
              stmt.addLabel($2.sval);
              stmt.emit();
              currTable = new SymbolTable($2.sval);
            }
    | FUNCTION ID PARENL fplist PARENR
      {
          //add label for new function
        ICode stmt = new ICode("NOP");
        stmt.addLabel($2.sval);
        stmt.emit();
        currTable = new SymbolTable($2.sval);
        currTable.add($4.sval, "int");
      }
      body
      {
        System.out.println(currTable);
      }
    ;

body: CURLL bodylist CURLR
    ;

fplist: ID COMMA fplist
    | ID
    ;

bodylist: vardecl bodylist
    | stmt bodylist
    | /* epsilon */
    ;

stmt: assign SEMICOLON  {stmtCount++;}
    | fcall SEMICOLON  {stmtCount++;}
    | while  {stmtCount++;}
    | if {stmtCount++;}
    | body
    ;

assign: ID ASSIGNOP expr
    ;

expr: factor
    | expr ADDOP factor
    ;

factor: term
    | factor MULOP term
    ;

term: ID {$$.sval = $1.sval;}
    | NUMBER
      {
        String newTemp = ICode.genTemp();
        String numberString = String.format("%d", $1.ival);
        ICode stmt = new ICode("MOV", numberString, newTemp);
        stmt.emit();
        currTable.add(newTemp, "int");
      }
    | PARENL expr PARENR
    | ADDOP term
    | fcall
    ;

bexpr: bfactor
    | bexpr OR bfactor
    ;

bfactor: bneg
    | bfactor AND bneg
    ;

bneg: bterm
    | NOT bterm
    ;

bterm: expr RELOP expr
    | PARENL bterm PARENR


fcall: ID PARENL PARENR
      {
        //ICode s = new ICode("Call" + $1.sval);
        //s.emit();
      }
    | ID PARENL aplist PARENR
    ;

aplist:expr COMMA aplist
    | expr
    | STRING
    ;


while: WHILE PARENL bexpr PARENR stmt
    ;

if: IF PARENL bexpr PARENR stmt elsepart
    ;

elsepart: ELSE stmt | /*Epsilon*/
%%

//##############################################################################

    public int stmtCount;
    public int funcCount;

    public SymbolTable currTable;
    public SymbolTable globalTable;

    private MyLexer yylexer;
    private Token t;

//##############################################################################

public void yyerror(String s)
{
    System.out.println("error found near line:"+t.getLine());
    System.out.println(" token:"+t.getValue());
    System.out.println(s);
}

//##############################################################################

public int yylex()
{
    try
	{
	    t = yylexer.nextToken();
	}
    catch (Exception e)
	{
	    System.err.println("yylex unable to aquire token");
	    return -1;
	}

    if (t==null)
	return -1;

    String tVal = t.getValue();
    switch(t.getType())
	{
	case Token.NUMBER:
	    yylval = new ParserVal(Integer.parseInt(tVal));
	    return NUMBER;
	case Token.ADDOP:
	    yylval = new ParserVal(tVal);
	    return ADDOP;
	case Token.MULOP:
	    yylval = new ParserVal(tVal);
	    return MULOP;
	case Token.RELOP:
	    yylval = new ParserVal(tVal);
	    return RELOP;
	case Token.ID:
	    yylval = new ParserVal(tVal);
	    return ID;
	case Token.PARENL:
	    return PARENL;
	case Token.PARENR:
	    return PARENR;
	case Token.COMMA:
	    return COMMA;
	case Token.ASSIGNOP:
	    return ASSIGNOP;
	case Token.SEMICOLON:
	    return SEMICOLON;
	case Token.IF:
	    return IF;
	case Token.WHILE:
	    return WHILE;
	case Token.ELSE:
	    return ELSE;
	case Token.CURLL:
	    return CURLL;
	case Token.CURLR:
	    return CURLR;
	case Token.VAR:
	    return VAR;
	case Token.FUNCTION:
	    return FUNCTION;
	case Token.OR:
	    return OR;
	case Token.AND:
	    return AND;
	case Token.NOT:
	    return NOT;
	case Token.STRING:
	    yylval = new ParserVal(tVal);
	    return STRING;
	default:
	    return -1;
	}
}

//##############################################################################

public void setup(String fname)
{
    yylexer = new MyLexer(fname);
    stmtCount = 0;
    funcCount = 0;

      //intialize gobal table
    globalTable = new SymbolTable("__GLOBAL__");
}

//##############################################################################

public static void main(String args[])
{
 Parser par = new Parser(false);
 par.setup(args[0]);
 par.yyparse();
}
