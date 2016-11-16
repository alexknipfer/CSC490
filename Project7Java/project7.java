%{
import java.lang.Math;
import java.io.*;
import java.util.*;
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
    | FUNCTION ID PARENL {currTable = new SymbolTable($2.sval);} fplist PARENR
      {
          //add label for new function
        ICode stmt = new ICode("NOP");
        stmt.addLabel($2.sval);
        stmt.emit();
      }
      body
      {
        System.out.println(currTable);
      }
    ;

body: CURLL bodylist CURLR
    ;

fplist: ID COMMA {currTable.add($1.sval, "int");} fplist
    | ID {currTable.add($1.sval, "int");}
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
        {
          ICode assignStmt = new ICode("MOV", $3.sval, $1.sval);
          assignStmt.emit();
        }
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
        String numberString = String.format("%d", $1.ival);
        $$.sval = numberString;
      }
    | PARENL expr PARENR {$$.sval = $2.sval;}
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
      {
        String temp, temp2;
        String newTemp = ICode.genTemp();
        currTable.add(newTemp, "int");
          //check if value is an integer
        if($2.sval.equals("<")){
          ICode lessThan = new ICode("LT", $1.sval, $3.sval, newTemp);
          lessThan.emit();
        }

        else if($2.sval.equals(">")){
          ICode greaterThan = new ICode("GT", $1.sval, $3.sval, newTemp);
          greaterThan.emit();
        }

        else if($2.sval.equals("<=")){
          ICode lessThanEqual = new ICode("LE", $1.sval, $3.sval, newTemp);
          lessThanEqual.emit();
        }

        else if($2.sval.equals(">=")){
          ICode greaterThanEqual = new ICode("GE", $1.sval, $3.sval, newTemp);
          greaterThanEqual.emit();
        }

        else if($2.sval.equals("==")){
          ICode equalTo = new ICode("EQ", $1.sval, $3.sval, newTemp);
          equalTo.emit();
        }

        ICode compare = new ICode("CMP", newTemp, "0");
        compare.emit();
        String newLabel = ICode.genLabel();
        elseLabelStack.push(newLabel);
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();
      }
    | PARENL bterm PARENR


fcall: ID PARENL PARENR
      {
        ICode callSimpleFunction = new ICode("CALL", $1.sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();
      }
    | ID PARENL aplist PARENR
      {
        ICode fCallParameters = new ICode("PARAM", $3.sval);
        fCallParameters.emit();
        ICode callSimpleFunction = new ICode("CALL", $1.sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();
      }
    ;

aplist:expr COMMA aplist
    | expr
    | STRING
    ;


while: WHILE
      {
        String topLabel = ICode.genLabel();
        ICode topStatement = new ICode("NOP");
        topStatement.addLabel(topLabel);
        topStatement.emit();
        whileLabelStack.push(topLabel);
      }
      PARENL bexpr PARENR
      {
        //ICode cmpPart = new ICode("CMP", $4.sval, "0");
        ICode cmpPart = new ICode("CMP", "TEMP_FOR_BEXPR", "0");
        cmpPart.emit();
        String outLabel = ICode.genLabel();
        ICode beqPart = new ICode("BEQ", outLabel);
        beqPart.emit();
        whileLabelStack.push(outLabel);
      }
      stmt
      {
        String outLabel = whileLabelStack.pop();
        String topLabel = whileLabelStack.pop();
        ICode backToTop = new ICode("BA", topLabel);
        backToTop.emit();
        ICode whileOut = new ICode("NOP");
        whileOut.addLabel(outLabel);
        whileOut.emit();
      }
    ;

if: IF PARENL bexpr PARENR
    stmt
    {
      //System.out.println($5.sval);
      //String branchAlwaysIfLbl = ICode.genLabel();
      //ICode branchAlwaysIf = new ICode("BA", branchAlwaysIfLbl);
      //branchAlwaysIf.emit();
    }
  elsepart
    ;

elsepart: ELSE
          {
            String elseLabel = elseLabelStack.pop();
            ICode elseBranch = new ICode("NOP");
            elseBranch.addLabel(elseLabel);
            elseBranch.emit();
          }
          stmt
        | /*Epsilon*/
%%

//##############################################################################

    public int stmtCount;
    public int funcCount;

    public SymbolTable currTable;
    public SymbolTable globalTable;

    public LinkedList<String> whileLabelStack;
    public LinkedList<String> elseLabelStack;

    private MyLexer yylexer;
    private Token t;

//##############################################################################

public void setup(String fname)
{
    yylexer = new MyLexer(fname);
    stmtCount = 0;
    funcCount = 0;

      //intialize gobal table
    globalTable = new SymbolTable("__GLOBAL__");
    whileLabelStack = new LinkedList<String>();
    elseLabelStack = new LinkedList<String>();
}

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

public static void main(String args[])
{
 Parser par = new Parser(false);
 par.setup(args[0]);
 par.yyparse();
}
