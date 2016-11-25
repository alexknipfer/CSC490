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
    System.out.println(globalTable);
    //System.out.println("The program is correct, and contains:");
    //System.out.printf("%5d statements\n",stmtCount);
    //System.out.printf("%5d function definitions\n",funcCount);
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
            //add variable to variable "stack"
          varStack.addLast($1.sval);
      }
    | ID
      {
          //add variable to variable stack
        varStack.addLast($1.sval);
      }
    ;

function: FUNCTION ID PARENL PARENR
            {
                //add function definition to global table
              globalTable.add($2.sval, "function");
                //add label for new funtion
              ICode stmt = new ICode("NOP");
              stmt.addLabel($2.sval);
              stmt.emit();
              currTable = new SymbolTable($2.sval);
            }
            body
            {
                //done with function body, "RETURN"
              ICode returnOp = new ICode("RET");
              returnOp.emit();

                //go through variable stack and add to table
              for(int x = 0; x < varStack.size(); x++){
                currTable.add(varStack.get(x), "int");
              }
                //clear for next function
              varStack.clear();
              System.out.println(currTable);
            }
    | FUNCTION ID PARENL {currTable = new SymbolTable($2.sval);} fplist PARENR
      {
          //add function definition to global table
        globalTable.add($2.sval, "function");
        
          //add label for new function
        ICode stmt = new ICode("NOP");
        stmt.addLabel($2.sval);
        stmt.emit();
      }
      body
      {
          //done with function body, "RETURN"
        ICode returnOp = new ICode("RET");
        returnOp.emit();

          //go through variable staack and add to table
        for(int x = 0; x < varStack.size(); x++){
          currTable.add(varStack.get(x), "int");
        }
          //clear for next function
        varStack.clear();
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
            //generate intermediate code for assignment operator
            //and emit the code
          ICode assignStmt = new ICode("MOV", $3.sval, $1.sval);
          assignStmt.emit();
        }
    ;

expr: factor
    | expr ADDOP factor
      {
          //create temp for result
        String tempAddOp = ICode.genTemp();
        currTable.add(tempAddOp, "int");

          //if the ADDOP is a subtraction, emit the code
        if($2.sval.equals("-")){
          ICode subt = new ICode("SUB", $1.sval, $3.sval, tempAddOp);
          subt.emit();
        }

          //if the ADDOP is an addition, emit the code
        else if($2.sval.equals("+")){
          ICode add = new ICode("ADD", $1.sval, $3.sval, tempAddOp);
          add.emit();
        }

          //return the result (stored in the temp)
        $$.sval = tempAddOp;
      }
    ;

factor: term
    | factor MULOP term
      {
          //generate temp for result of MULOP
        String tempMulOp = ICode.genTemp();
        currTable.add(tempMulOp, "int");

          //check to see if token is multiplication
        if($2.sval.equals("*")){
          ICode multiply = new ICode("MUL", $1.sval, $3.sval, tempMulOp);
          multiply.emit();
        }

          //check to see if token is division
        else if($2.sval.equals("/")){
          ICode divide = new ICode("DIV", $1.sval, $3.sval, tempMulOp);
          divide.emit();
        }
          //return the result
        $$.sval = tempMulOp;
      }
    ;

term: ID {$$.sval = $1.sval;}
    | NUMBER
      {
          //convert numbers to string
        String numberString = String.format("%d", $1.ival);
        $$.sval = numberString;
      }
    | PARENL expr PARENR {$$.sval = $2.sval;}
    | ADDOP term
      {
          //generate temp for negative value
        String negTemp = ICode.genTemp();
        currTable.add(negTemp, "int");

          //convert number to string
        String tempNumber = String.format("%d", $2.ival);
        ICode negValue = new ICode("NEG", tempNumber, negTemp);
        negValue.emit();

          //return the value
        $$.sval = negTemp;
      }
    | fcall
    ;

bexpr: bfactor
    | bexpr OR bfactor
      {
          //compare the two temps from for the comparison to branch
        ICode andCmp = new ICode("CMP", cmpStack.pop(), cmpStack.pop());
        andCmp.emit();
        String newLabel = ICode.genLabel();

          //if false, branch out
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();
      }
    ;

bfactor: bneg
    | bfactor AND bneg
      {
          //compare the two temps from for the comparison to branch
        ICode andCmp = new ICode("CMP", cmpStack.pop(), cmpStack.pop());
        andCmp.emit();

          //if false, branch out
        ICode branchOnEqual = new ICode("BE", genLabelStack.pop());
        branchOnEqual.emit();
      }
    ;

bneg: bterm
    | NOT bterm
      {
          //peform a logical NOT
        String notTemp = ICode.genTemp();
        ICode logicalNot = new ICode("MOV", "NOT", notTemp, notTemp);
        logicalNot.emit();
      }
    ;

bterm: expr RELOP expr
      {
        String temp, temp2;

          //generate comparison intermediate code
        String newTemp = ICode.genTemp();
        cmpStack.push(newTemp);

          //add temp to table
        currTable.add(newTemp, "int");

          //generate code for less than comparison
        if($2.sval.equals("<")){
          ICode lessThan = new ICode("LT", $1.sval, $3.sval, newTemp);
          lessThan.emit();
        }

          //generate code for greater than comparison
        else if($2.sval.equals(">")){
          ICode greaterThan = new ICode("GT", $1.sval, $3.sval, newTemp);
          greaterThan.emit();
        }

          //generate code for less than equal comparison
        else if($2.sval.equals("<=")){
          ICode lessThanEqual = new ICode("LE", $1.sval, $3.sval, newTemp);
          lessThanEqual.emit();
        }

          //generate code for greater than equal
        else if($2.sval.equals(">=")){
          ICode greaterThanEqual = new ICode("GE", $1.sval, $3.sval, newTemp);
          greaterThanEqual.emit();
        }

          //generate code for equal to
        else if($2.sval.equals("==")){
          ICode equalTo = new ICode("EQ", $1.sval, $3.sval, newTemp);
          equalTo.emit();
        }

          //generate code for NOT equal
        else if($2.sval.equals("!=")){
          ICode notEqual = new ICode("NEQ", $1.sval, $3.sval, newTemp);
          notEqual.emit();
        }

          //compare the results
        ICode compare = new ICode("CMP", newTemp, "0");
        compare.emit();
        String newLabel = ICode.genLabel();
        genLabelStack.push(newLabel);

          //branch if result is true
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();
      }
    | PARENL bterm PARENR


fcall: ID PARENL PARENR
      {
          //generate intermediate code for function
          //with no parameters
        ICode callSimpleFunction = new ICode("CALL", $1.sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();

          //return the result
        $$.sval = stretTemp;
      }
    | ID PARENL aplist PARENR
      {
          //generate intermediate code for function with parameters
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");

          //if there is no values currently stored, generate the intermediate code
        ICode fCallParameters = new ICode("PARAM", $3.sval);
        fCallParameters.emit();

          //generate CALL intermediate code and print
        ICode callSimpleFunction = new ICode("CALL", $1.sval);
        callSimpleFunction.emit();
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();

          //return temp variable
        $$.sval = stretTemp;
      }
    ;

aplist:expr COMMA aplist
    | expr
    | STRING
    ;


while: WHILE
      {
          //generate label for while loop
        String topLabel = ICode.genLabel();
        ICode topStatement = new ICode("NOP");
        topStatement.addLabel(topLabel);
        topStatement.emit();

          //add the label to the stack
        whileLabelStack.push(topLabel);
      }
      PARENL bexpr PARENR
      stmt
      {
          //jump back to the top
        ICode jump = new ICode("JMP", whileLabelStack.pop());
        jump.emit();

          //get past the while loop
        ICode pastWhileLoop = new ICode("NOP");
        pastWhileLoop.addLabel(genLabelStack.pop());
        pastWhileLoop.emit();
      }
    ;

if: IF PARENL bexpr PARENR stmt elsepart
    ;

elsepart: ELSE
          {
              //generate label for branching to else
            String branchAlwaysIfLbl = ICode.genLabel();
            branchAlwaysStack.push(branchAlwaysIfLbl);

              //there is always a branch to get to else statement
            ICode branchAlwaysIf = new ICode("BA", branchAlwaysIfLbl);
            branchAlwaysIf.emit();

              //branch to get to ELSE
            String elseLabel = genLabelStack.pop();
            ICode elseBranch = new ICode("NOP");
            elseBranch.addLabel(elseLabel);
            elseBranch.emit();
          }
          stmt
          {
              //create a branch to get past the IF statement
            ICode afterElse = new ICode("NOP");
            afterElse.addLabel(branchAlwaysStack.pop());
            afterElse.emit();
          }
        | /*Epsilon*/
%%

//##############################################################################

    public int stmtCount;
    public int funcCount;

    public SymbolTable currTable;
    public SymbolTable globalTable;

    public LinkedList<String> whileLabelStack;
    public LinkedList<String> genLabelStack;
    public LinkedList<String> branchAlwaysStack;
    public LinkedList<String> varStack;
    public LinkedList<String> cmpStack;

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
    genLabelStack = new LinkedList<String>();
    branchAlwaysStack = new LinkedList<String>();
    varStack = new LinkedList<String>();
    cmpStack = new LinkedList<String>();
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
 for(ICode c: ICode.stmtList){
   System.out.print("#");
   c.print();

   switch(c.getOpCode()){
    case "NOP": System.out.println("_" + c.getLabel() + ":");
                break;
    case "RET": System.out.println("retq");
                break;
   }

   List<String> operands = c.getOperands();
   if(operands.size()>=1){
     System.out.println(operands.get(0));
   }

   System.out.println();
 }
}
