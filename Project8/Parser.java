//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "project8.java"
import java.lang.Math;
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
//#line 22 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short ADDOP=257;
public final static short AND=258;
public final static short ASSIGNOP=259;
public final static short COMMA=260;
public final static short CURLL=261;
public final static short CURLR=262;
public final static short ELSE=263;
public final static short FUNCTION=264;
public final static short ID=265;
public final static short IF=266;
public final static short MULOP=267;
public final static short NOT=268;
public final static short NUMBER=269;
public final static short OR=270;
public final static short PARENL=271;
public final static short PARENR=272;
public final static short RELOP=273;
public final static short SEMICOLON=274;
public final static short STRING=275;
public final static short VAR=276;
public final static short WHILE=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    3,    5,    5,    7,    4,
    9,   10,    4,    6,   12,    8,    8,   11,   11,   11,
   13,   13,   13,   13,   13,   14,   18,   18,   19,   19,
   20,   20,   20,   20,   20,   21,   21,   22,   22,   23,
   23,   24,   24,   15,   15,   25,   25,   25,   26,   16,
   17,   28,   27,   27,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    0,    6,
    0,    0,    8,    3,    0,    4,    1,    2,    2,    0,
    2,    2,    1,    1,    1,    3,    1,    3,    1,    3,
    1,    1,    3,    2,    1,    1,    3,    1,    3,    1,
    2,    3,    3,    3,    4,    3,    1,    1,    0,    6,
    6,    0,    3,    0,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    1,    0,    4,    5,    0,    0,    0,
    2,    0,    0,    6,    9,    0,    7,    0,    0,    0,
    0,   10,   15,   12,    0,    0,   49,    0,   25,    0,
    0,    0,    0,   23,   24,    0,    0,    0,    0,    0,
    0,   18,   14,   19,   21,   22,   16,   13,    0,    0,
   32,    0,   35,    0,    0,   29,   44,   48,    0,    0,
    0,    0,    0,    0,    0,   38,   40,    0,   34,    0,
    0,    0,    0,   45,   41,    0,    0,    0,    0,    0,
    0,    0,   33,    0,   30,   46,   43,    0,    0,    0,
   39,    0,   52,   51,   50,    0,   53,
};
final static short yydgoto[] = {                          3,
    4,    5,   28,    7,   10,   29,   18,   20,   16,   37,
   30,   36,   31,   32,   53,   34,   35,   63,   55,   56,
   64,   65,   66,   67,   60,   41,   94,   96,
};
final static short yysindex[] = {                      -215,
 -260, -225,    0,    0, -215,    0,    0, -201, -204, -194,
    0, -163, -225,    0,    0, -153,    0, -145, -142, -152,
 -187,    0,    0,    0, -157, -150,    0, -187,    0, -143,
 -187, -151, -149,    0,    0, -153, -145, -173, -251, -174,
 -147,    0,    0,    0,    0,    0,    0,    0, -173, -144,
    0, -173,    0, -135, -141,    0,    0,    0, -203, -140,
 -164, -164, -222, -261, -130,    0,    0, -174,    0, -214,
 -173, -173, -238,    0,    0, -191, -139, -173, -174, -249,
 -174, -166,    0, -141,    0,    0,    0, -135, -130, -134,
    0, -249,    0,    0,    0, -249,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  130,    0,    0,    0, -138,    0,
    0, -131,    0,    0,    0,    0,    0,    0, -137,    0,
 -125,    0,    0,    0,    0,    0,    0, -125,    0,    0,
 -125,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -228,
    0,    0,    0, -136, -210,    0,    0,    0, -133,    0,
    0,    0,    0,    0, -162,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -205,    0,    0,    0, -159, -155, -189,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  126,    0,    8,    0,  127,  -14,    0,  105,    0,    0,
   72,    0,  -58,    0,  -21,    0,    0,  -37,   71,  -46,
   75,   65,   64,   24,   73,    0,    0,    0,
};
final static int YYTABLESIZE=146;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   54,   59,   69,   22,    8,   49,   33,    6,   79,   33,
   80,   21,    6,   50,   70,   25,   26,   51,   49,   52,
   57,   90,   48,   58,   76,   85,   50,   27,   31,   31,
   51,   31,   52,   95,   71,   59,   58,   97,   31,    9,
   88,   31,   71,   31,   31,   31,   27,   27,    1,   27,
   78,   28,   28,   71,   28,   13,   73,   83,   33,   27,
    2,   27,   27,   27,   28,   71,   28,   28,   28,   12,
   33,   54,   54,   21,   33,   54,   54,   25,   26,   14,
   83,   78,   49,   49,   75,   77,   54,   54,    2,   27,
   50,   50,   49,   61,   51,   51,   62,   52,   42,   42,
   50,   38,   44,   79,   51,   92,   62,   36,   15,   36,
   42,   19,   42,   39,   37,   21,   37,   23,   43,   24,
   40,   71,   45,   68,   46,   72,   39,   81,   93,    3,
   11,   74,   87,   11,   17,    8,   20,   26,   47,   17,
   47,   84,   82,   89,   91,   86,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         21,
   38,   39,   49,   18,  265,  257,   28,    0,  270,   31,
  272,  261,    5,  265,   52,  265,  266,  269,  257,  271,
  272,   80,   37,  275,   62,   72,  265,  277,  257,  258,
  269,  260,  271,   92,  257,   73,  275,   96,  267,  265,
   78,  270,  257,  272,  273,  274,  257,  258,  264,  260,
  273,  257,  258,  257,  260,  260,  260,  272,   80,  270,
  276,  272,  273,  274,  270,  257,  272,  273,  274,  271,
   92,  261,  262,  261,   96,  265,  266,  265,  266,  274,
  272,  273,  257,  257,   61,   62,  276,  277,  276,  277,
  265,  265,  257,  268,  269,  269,  271,  271,  258,   28,
  265,  259,   31,  270,  269,  272,  271,  270,  272,  272,
  270,  265,  272,  271,  270,  261,  272,  260,  262,  272,
  271,  257,  274,  271,  274,  267,  271,  258,  263,    0,
    5,  272,  272,  265,  272,  274,  262,  274,  272,   13,
   36,   71,   68,   79,   81,   73,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"ADDOP","AND","ASSIGNOP","COMMA","CURLL","CURLR","ELSE",
"FUNCTION","ID","IF","MULOP","NOT","NUMBER","OR","PARENL","PARENR","RELOP",
"SEMICOLON","STRING","VAR","WHILE",
};
final static String yyrule[] = {
"$accept : start",
"start : pgm",
"pgm : pgmpart pgm",
"pgm : pgmpart",
"pgmpart : vardecl",
"pgmpart : function",
"vardecl : VAR varlist SEMICOLON",
"varlist : ID COMMA varlist",
"varlist : ID",
"$$1 :",
"function : FUNCTION ID PARENL PARENR $$1 body",
"$$2 :",
"$$3 :",
"function : FUNCTION ID PARENL $$2 fplist PARENR $$3 body",
"body : CURLL bodylist CURLR",
"$$4 :",
"fplist : ID COMMA $$4 fplist",
"fplist : ID",
"bodylist : vardecl bodylist",
"bodylist : stmt bodylist",
"bodylist :",
"stmt : assign SEMICOLON",
"stmt : fcall SEMICOLON",
"stmt : while",
"stmt : if",
"stmt : body",
"assign : ID ASSIGNOP expr",
"expr : factor",
"expr : expr ADDOP factor",
"factor : term",
"factor : factor MULOP term",
"term : ID",
"term : NUMBER",
"term : PARENL expr PARENR",
"term : ADDOP term",
"term : fcall",
"bexpr : bfactor",
"bexpr : bexpr OR bfactor",
"bfactor : bneg",
"bfactor : bfactor AND bneg",
"bneg : bterm",
"bneg : NOT bterm",
"bterm : expr RELOP expr",
"bterm : PARENL bterm PARENR",
"fcall : ID PARENL PARENR",
"fcall : ID PARENL aplist PARENR",
"aplist : expr COMMA aplist",
"aplist : expr",
"aplist : STRING",
"$$5 :",
"while : WHILE $$5 PARENL bexpr PARENR stmt",
"if : IF PARENL bexpr PARENR stmt elsepart",
"$$6 :",
"elsepart : ELSE $$6 stmt",
"elsepart :",
};

//#line 438 "project8.java"

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

     try{
      Integer.parseInt(currentOp);
        //move parameter value into register
      ICode param = new ICode("movl", "$" + currentOp, "%eax");
      param.print();
     }
      //not a number...
     catch (NumberFormatException ex){
      Symbol currSymbol = currentTable.find(currentOp);
      String currOffset = String.format("%d", currSymbol.getOffset());
      String doOffset = "-" + currOffset + "(%rbp)";

      ICode paramVar = new ICode("movl", doOffset, "%eax");
      paramVar.print();
     }
   }

//*********************** MOV ***************************************

   else if(c.getOpCode() == "MOV"){
      //see if the value is a number
     try{
      Integer.parseInt(currentOp);
      Symbol currSymbol = currentTable.find(currentOp2);
      String currOffset = String.format("%d", currSymbol.getOffset());
      String doOffset = "-" + currOffset + "(%rbp)";

      ICode movlNumber = new ICode("movl", "$" + currentOp, "%eax");
      ICode movlTemp = new ICode("movl", "%eax", doOffset);

      movlNumber.print();
      movlTemp.print();
     }
      //not a number...
     catch (NumberFormatException ex){
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
  if(par.isNumber(currentOp)){
    System.out.println("is a number!!!");
  }
  else{
    System.out.println("NOT a number");
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
//#line 591 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 38 "project8.java"
{
    System.out.println(globalTable);
    /*System.out.println("The program is correct, and contains:");*/
    /*System.out.printf("%5d statements\n",stmtCount);*/
    /*System.out.printf("%5d function definitions\n",funcCount);*/
}
break;
case 5:
//#line 51 "project8.java"
{funcCount++;}
break;
case 7:
//#line 58 "project8.java"
{
            /*add variable to variable "stack"*/
          varStack.addLast(val_peek(2).sval);
      }
break;
case 8:
//#line 63 "project8.java"
{
          /*add variable to variable stack*/
        varStack.addLast(val_peek(0).sval);
      }
break;
case 9:
//#line 70 "project8.java"
{
                /*add function definition to global table*/
              globalTable.add(val_peek(2).sval, "function");
                /*add label for new funtion*/
              ICode stmt = new ICode("NOP");
              stmt.addLabel(val_peek(2).sval);
              stmt.emit();
              currTable = new SymbolTable(val_peek(2).sval);
              myTables.push(currTable);
            }
break;
case 10:
//#line 81 "project8.java"
{
                /*done with function body, "RETURN"*/
              ICode returnOp = new ICode("RET");
              returnOp.emit();

                /*go through variable stack and add to table*/
              for(int x = 0; x < varStack.size(); x++){
                currTable.add(varStack.get(x), "int");
              }
                /*clear for next function*/
              varStack.clear();
              System.out.println(currTable);
            }
break;
case 11:
//#line 95 "project8.java"
{
          currTable = new SymbolTable(val_peek(1).sval);
          myTables.push(currTable);
        }
break;
case 12:
//#line 100 "project8.java"
{
          /*add function definition to global table*/
        globalTable.add(val_peek(4).sval, "function");
        
          /*add label for new function*/
        ICode stmt = new ICode("NOP");
        stmt.addLabel(val_peek(4).sval);
        stmt.emit();

        ICode paramList = new ICode("PLIST", val_peek(1).sval);
        paramList.emit();
      }
break;
case 13:
//#line 113 "project8.java"
{
          /*done with function body, "RETURN"*/
        ICode returnOp = new ICode("RET");
        returnOp.emit();

          /*go through variable staack and add to table*/
        for(int x = 0; x < varStack.size(); x++){
          currTable.add(varStack.get(x), "int");
        }
          /*clear for next function*/
        varStack.clear();
        System.out.println(currTable);
      }
break;
case 15:
//#line 132 "project8.java"
{
    currTable.add(val_peek(1).sval, "int");
    yyval.sval = val_peek(1).sval;
  }
break;
case 17:
//#line 138 "project8.java"
{
      currTable.add(val_peek(0).sval, "int");
      yyval.sval = val_peek(0).sval;
    }
break;
case 21:
//#line 149 "project8.java"
{stmtCount++;}
break;
case 22:
//#line 150 "project8.java"
{stmtCount++;}
break;
case 23:
//#line 151 "project8.java"
{stmtCount++;}
break;
case 24:
//#line 152 "project8.java"
{stmtCount++;}
break;
case 26:
//#line 157 "project8.java"
{
            /*generate intermediate code for assignment operator*/
            /*and emit the code*/
          String tempAssignOp = ICode.genTemp();
          currTable.add(tempAssignOp, "int");
          ICode assignStmt = new ICode("MOV", val_peek(0).sval, tempAssignOp);
          ICode assignStmt2 = new ICode("MOV", tempAssignOp, val_peek(2).sval);
          assignStmt.emit();
          assignStmt2.emit();
        }
break;
case 28:
//#line 171 "project8.java"
{
          /*create temp for result*/
        String tempAddOp = ICode.genTemp();
        currTable.add(tempAddOp, "int");

          /*if the ADDOP is a subtraction, emit the code*/
        if(val_peek(1).sval.equals("-")){
          ICode subt = new ICode("SUB", val_peek(2).sval, val_peek(0).sval, tempAddOp);
          subt.emit();
        }

          /*if the ADDOP is an addition, emit the code*/
        else if(val_peek(1).sval.equals("+")){
          ICode add = new ICode("ADD", val_peek(2).sval, val_peek(0).sval, tempAddOp);
          add.emit();
        }

          /*return the result (stored in the temp)*/
        yyval.sval = tempAddOp;
      }
break;
case 30:
//#line 195 "project8.java"
{
          /*generate temp for result of MULOP*/
        String tempMulOp = ICode.genTemp();
        currTable.add(tempMulOp, "int");

          /*check to see if token is multiplication*/
        if(val_peek(1).sval.equals("*")){
          ICode multiply = new ICode("MUL", val_peek(2).sval, val_peek(0).sval, tempMulOp);
          multiply.emit();
        }

          /*check to see if token is division*/
        else if(val_peek(1).sval.equals("/")){
          ICode divide = new ICode("DIV", val_peek(2).sval, val_peek(0).sval, tempMulOp);
          divide.emit();
        }
          /*return the result*/
        yyval.sval = tempMulOp;
      }
break;
case 31:
//#line 216 "project8.java"
{yyval.sval = val_peek(0).sval;}
break;
case 32:
//#line 218 "project8.java"
{
          /*convert numbers to string*/
        String numberString = String.format("%d", val_peek(0).ival);
        yyval.sval = numberString;
      }
break;
case 33:
//#line 223 "project8.java"
{yyval.sval = val_peek(1).sval;}
break;
case 34:
//#line 225 "project8.java"
{
          /*generate temp for negative value*/
        String negTemp = ICode.genTemp();
        currTable.add(negTemp, "int");

          /*convert number to string*/
        String tempNumber = String.format("%d", val_peek(0).ival);
        ICode negValue = new ICode("NEG", tempNumber, negTemp);
        negValue.emit();

          /*return the value*/
        yyval.sval = negTemp;
      }
break;
case 37:
//#line 243 "project8.java"
{
          /*compare the two temps from for the comparison to branch*/
        ICode andCmp = new ICode("CMP", cmpStack.pop(), cmpStack.pop());
        andCmp.emit();
        String newLabel = ICode.genLabel();

          /*if false, branch out*/
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();
      }
break;
case 39:
//#line 257 "project8.java"
{
          /*compare the two temps from for the comparison to branch*/
        ICode andCmp = new ICode("CMP", cmpStack.pop(), cmpStack.pop());
        andCmp.emit();

          /*if false, branch out*/
        ICode branchOnEqual = new ICode("BE", genLabelStack.pop());
        branchOnEqual.emit();
      }
break;
case 41:
//#line 270 "project8.java"
{
          /*peform a logical NOT*/
        String notTemp = ICode.genTemp();
        ICode logicalNot = new ICode("MOV", "NOT", notTemp, notTemp);
        logicalNot.emit();
      }
break;
case 42:
//#line 279 "project8.java"
{
        String temp, temp2;

          /*generate comparison intermediate code*/
        String newTemp = ICode.genTemp();
        cmpStack.push(newTemp);

          /*add temp to table*/
        currTable.add(newTemp, "int");

          /*generate code for less than comparison*/
        if(val_peek(1).sval.equals("<")){
          ICode lessThan = new ICode("LT", val_peek(2).sval, val_peek(0).sval, newTemp);
          lessThan.emit();
        }

          /*generate code for greater than comparison*/
        else if(val_peek(1).sval.equals(">")){
          ICode greaterThan = new ICode("GT", val_peek(2).sval, val_peek(0).sval, newTemp);
          greaterThan.emit();
        }

          /*generate code for less than equal comparison*/
        else if(val_peek(1).sval.equals("<=")){
          ICode lessThanEqual = new ICode("LE", val_peek(2).sval, val_peek(0).sval, newTemp);
          lessThanEqual.emit();
        }

          /*generate code for greater than equal*/
        else if(val_peek(1).sval.equals(">=")){
          ICode greaterThanEqual = new ICode("GE", val_peek(2).sval, val_peek(0).sval, newTemp);
          greaterThanEqual.emit();
        }

          /*generate code for equal to*/
        else if(val_peek(1).sval.equals("==")){
          ICode equalTo = new ICode("EQ", val_peek(2).sval, val_peek(0).sval, newTemp);
          equalTo.emit();
        }

          /*generate code for NOT equal*/
        else if(val_peek(1).sval.equals("!=")){
          ICode notEqual = new ICode("NEQ", val_peek(2).sval, val_peek(0).sval, newTemp);
          notEqual.emit();
        }

          /*compare the results*/
        ICode compare = new ICode("CMP", newTemp, "0");
        compare.emit();
        String newLabel = ICode.genLabel();
        genLabelStack.push(newLabel);

          /*branch if result is true*/
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();
      }
break;
case 44:
//#line 339 "project8.java"
{
          /*generate intermediate code for function*/
          /*with no parameters*/
        ICode callSimpleFunction = new ICode("CALL", val_peek(2).sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();

          /*return the result*/
        yyval.sval = stretTemp;
      }
break;
case 45:
//#line 353 "project8.java"
{
          /*generate intermediate code for function with parameters*/
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");

          /*if there is no values currently stored, generate the intermediate code*/
        ICode fCallParameters = new ICode("PARAM", val_peek(1).sval);
        fCallParameters.emit();

          /*generate CALL intermediate code and print*/
        ICode callSimpleFunction = new ICode("CALL", val_peek(3).sval);
        callSimpleFunction.emit();
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();

          /*return temp variable*/
        yyval.sval = stretTemp;
      }
break;
case 49:
//#line 380 "project8.java"
{
          /*generate label for while loop*/
        String topLabel = ICode.genLabel();
        ICode topStatement = new ICode("NOPWHILE");
        topStatement.addLabel(topLabel);
        topStatement.emit();

          /*add the label to the stack*/
        whileLabelStack.push(topLabel);
      }
break;
case 50:
//#line 392 "project8.java"
{
          /*jump back to the top*/
        ICode jump = new ICode("JMP", whileLabelStack.pop());
        jump.emit();

          /*get past the while loop*/
        ICode pastWhileLoop = new ICode("NOP");
        pastWhileLoop.addLabel(genLabelStack.pop());
        pastWhileLoop.emit();
      }
break;
case 52:
//#line 408 "project8.java"
{
              /*generate label for branching to else*/
            String branchAlwaysIfLbl = ICode.genLabel();
            branchAlwaysStack.push(branchAlwaysIfLbl);

              /*there is always a branch to get to else statement*/
            ICode branchAlwaysIf = new ICode("BA", branchAlwaysIfLbl);
            branchAlwaysIf.emit();

              /*branch to get to ELSE*/
            String elseLabel = genLabelStack.pop();
            ICode elseBranch = new ICode("NOPELSE");
            elseBranch.addLabel(elseLabel);
            elseBranch.emit();
          }
break;
case 53:
//#line 424 "project8.java"
{
              /*create a branch to get past the IF statement*/
            ICode afterElse = new ICode("NOP");
            afterElse.addLabel(branchAlwaysStack.pop());
            afterElse.emit();
          }
break;
case 54:
//#line 431 "project8.java"
{
            String afterIfLbl = ICode.genLabel();
            ICode afterIf = new ICode("NOPIF");
            afterIf.addLabel(genLabelStack.pop());
            afterIf.emit();
          }
break;
//#line 1146 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
