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
              myTables.push(currTable);
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
    | FUNCTION ID PARENL 
        {
          currTable = new SymbolTable($2.sval);
          myTables.push(currTable);
        } 
      fplist PARENR
      {
          //add function definition to global table
        globalTable.add($2.sval, "function");
        
          //add label for new function
        ICode stmt = new ICode("NOP");
        stmt.addLabel($2.sval);
        stmt.emit();

        ICode paramList = new ICode("PLIST", $5.sval);
        paramList.emit();
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

fplist: ID COMMA 
  {
    currTable.add($1.sval, "int");
    $$.sval = $1.sval;
  } 
  fplist
    | ID 
    {
      currTable.add($1.sval, "int");
      $$.sval = $1.sval;
    }
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
          String tempAssignOp = ICode.genTemp();
          currTable.add(tempAssignOp, "int");
          ICode assignStmt = new ICode("MOV", $3.sval, tempAssignOp);
          ICode assignStmt2 = new ICode("MOV", tempAssignOp, $1.sval);
          assignStmt.emit();
          assignStmt2.emit();
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

        String newLabel = ICode.genLabel();
        genLabelStack.push(newLabel);

          //add temp to table
        currTable.add(newTemp, "int");

          //generate code for less than comparison
        if($2.sval.equals("<")){
          ICode lessThan = new ICode("LT", $1.sval, $3.sval, newLabel);
          lessThan.emit();
        }

          //generate code for greater than comparison
        else if($2.sval.equals(">")){
          ICode greaterThan = new ICode("GT", $1.sval, $3.sval, newLabel);
          greaterThan.emit();
        }

          //generate code for less than equal comparison
        else if($2.sval.equals("<=")){
          ICode lessThanEqual = new ICode("LE", $1.sval, $3.sval, newLabel);
          lessThanEqual.emit();
        }

          //generate code for greater than equal
        else if($2.sval.equals(">=")){
          ICode greaterThanEqual = new ICode("GE", $1.sval, $3.sval, newLabel);
          greaterThanEqual.emit();
        }

          //generate code for equal to
        else if($2.sval.equals("==")){
          ICode equalTo = new ICode("EQ", $1.sval, $3.sval, newLabel);
          equalTo.emit();
        }

          //generate code for NOT equal
        else if($2.sval.equals("!=")){
          ICode notEqual = new ICode("NEQ", $1.sval, $3.sval, newLabel);
          notEqual.emit();
        }

          //compare the results
        /*ICode compare = new ICode("CMP", newTemp, "0");
        compare.emit();
        String newLabel = ICode.genLabel();
        genLabelStack.push(newLabel);
          //branch if result is true
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();*/
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
        ICode topStatement = new ICode("NOPWHILE");
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
            ICode elseBranch = new ICode("NOPELSE");
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
          {
            String afterIfLbl = ICode.genLabel();
            ICode afterIf = new ICode("NOPIF");
            afterIf.addLabel(genLabelStack.pop());
            afterIf.emit();
          }
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

    public static LinkedList<SymbolTable> myTables;

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

    myTables = new LinkedList<SymbolTable>();
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

public boolean isNumber(String currentOp){
  try{
    Integer.parseInt(currentOp);
    return true;
  }
  catch(NumberFormatException ex){
    return false;
  }
}


//##############################################################################

public static void main(String args[])
{
 Parser par = new Parser(false);
 par.setup(args[0]);
 par.yyparse();

 for(ICode list: ICode.stmtList){
   list.print();
 }

  //go through all intermediate code statements
 for(ICode c: ICode.stmtList){
   String currentOp = "";
   String currentOp2 = "";
   String currentOp3 = "";

    //get the current table
   SymbolTable currentTable = myTables.getLast();
   String tableSize = String.format("%d", currentTable.getSize());

    //print the intermediate code as a comment
   System.out.print("#");
   c.print();

   List<String> operands = c.getOperands();

      //current intermediate code has 1 operand
    if(operands.size()>=1){
      currentOp = operands.get(0);
    }

      //current intermediate code has 2 operands
    if(operands.size()>=2){
      currentOp = operands.get(0);
      currentOp2 = operands.get(1);
    }

      //current intermediate code has 3 operands
    if(operands.size()>=3){
      currentOp = operands.get(0);
      currentOp2 = operands.get(1);
      currentOp3 = operands.get(2);
    }

//********************* NOP ******************************************

    //hit a new function / label
   if(c.getOpCode() == "NOP"){
     System.out.println(c.getLabel() + ":");
     ICode newReg = new ICode("pushq", "%rbp");
     ICode newFunc = new ICode("movq", "%rsp", "%rbp");
     ICode subq = new ICode("subq", "$" + tableSize, "%rsp");
     newReg.print();
     newFunc.print();
     subq.print();
   }

//********************** PARAM *****************************************

    //process parameters
   else if(c.getOpCode() == "PARAM"){

     if(par.isNumber(currentOp)){
         //move parameter value into register
      ICode param = new ICode("movl", "$" + currentOp, "%eax");
      param.print();
     }

     else{
      Symbol currSymbol = currentTable.find(currentOp);
      String currOffset = String.format("%d", currSymbol.getOffset());
      String doOffset = "-" + currOffset + "(%rbp)";

      ICode paramVar = new ICode("movl", doOffset, "%eax");
      paramVar.print();
     }
   }

//*********************** MOV ***************************************

   else if(c.getOpCode() == "MOV"){
      
      if(par.isNumber(currentOp)){
        Symbol currSymbol = currentTable.find(currentOp2);
        String currOffset = String.format("%d", currSymbol.getOffset());
        String doOffset = "-" + currOffset + "(%rbp)";

        ICode movlNumber = new ICode("movl", "$" + currentOp, "%eax");
        ICode movlTemp = new ICode("movl", "%eax", doOffset);

        movlNumber.print();
        movlTemp.print();
      }

      else{
        Symbol currSymbol = currentTable.find(currentOp);
        String currOffset = String.format("%d", currSymbol.getOffset());
        String doOffset = "-" + currOffset + "(%rbp)";
        Symbol currSymbol2 = currentTable.find(currentOp2);
        String currOffset2 = String.format("%d", currSymbol2.getOffset());
        String doOffset2 = "-" + currOffset2 + "(%rbp)";
        ICode move = new ICode("movl", doOffset, "%eax");
        ICode move2 = new ICode("movl", "%eax", doOffset2);
        move.print();
        move2.print();
      }
   }

//************************** LT *************************************

else if(c.getOpCode() == "LT"){

    //1st operator is a number
  if(par.isNumber(currentOp)){

      //1st number is operator, 2nd number is operator
    if(par.isNumber(currentOp2)){
      ICode cmp3 = new ICode("cmp", "$" + currentOp2, "$" + currentOp);
      cmp3.print();
    }

      //1st operator is number, 2nd is NOT
    else{
      Symbol currSymbol3 = currentTable.find(currentOp2);
      String currOffset3 = String.format("%d", currSymbol3.getOffset());
      String doOffset3 = "-" + currOffset3 + "(%rbp)";
      ICode cmp4 = new ICode("cmp", doOffset3, "$" + currentOp);
      cmp4.print();
    }
  }

    //1st operator is not a number
  else{

      //get the 1st operator's offset
    Symbol currSymbol = currentTable.find(currentOp);
    String currOffset = String.format("%d", currSymbol.getOffset());
    String doOffset = "-" + currOffset + "(%rbp)";

      //1st operator is not an number, 2nd is a number
    if(par.isNumber(currentOp2)){
      ICode cmp1 = new ICode("cmp", "$" + currentOp2, doOffset);
      cmp1.print();
    }
      //1st operator is not a number, 2nd is not a number
    else{
      Symbol currSymbol2 = currentTable.find(currentOp2);
      String currOffset2 = String.format("%d", currSymbol2.getOffset());
      String doOffset2 = "-" + currOffset2 + "(%rbp)";
      ICode cmp2 = new ICode("cmp", doOffset2, doOffset);
      cmp2.print();
    }
  }
}


//************************** CALL *************************************

    //call the current function
   else if(c.getOpCode() == "CALL"){
     ICode callFunc = new ICode("call", currentOp);
     callFunc.print();
   }

//************************** STRET *************************************

  else if(c.getOpCode() == "STRET"){
    Symbol currSymbol = currentTable.find(currentOp);
    String currOffset = String.format("%d", currSymbol.getOffset());
    String doOffset = "-" + currOffset + "(%rbp)";

    ICode storet = new ICode("movl", "%eax", doOffset);
    storet.print();
  }

//************************ RET    *************************************

    //end of function reached, return!
   else if(c.getOpCode() == "RET"){
     ICode leave = new ICode("leave");
     ICode ret = new ICode("retq");
     leave.print();
     ret.print();
   }

   System.out.println();
 }
}
