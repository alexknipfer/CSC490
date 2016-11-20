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






//#line 2 "project7.java"
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
   23,   24,   24,   15,   15,   25,   25,   25,   26,   27,
   16,   17,   29,   28,   28,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    0,    6,
    0,    0,    8,    3,    0,    4,    1,    2,    2,    0,
    2,    2,    1,    1,    1,    3,    1,    3,    1,    3,
    1,    1,    3,    2,    1,    1,    3,    1,    3,    1,
    2,    3,    3,    3,    4,    3,    1,    1,    0,    0,
    7,    6,    0,    3,    0,
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
   39,   50,   53,   52,    0,    0,   51,   54,
};
final static short yydgoto[] = {                          3,
    4,    5,   28,    7,   10,   29,   18,   20,   16,   37,
   30,   36,   31,   32,   53,   34,   35,   63,   55,   56,
   64,   65,   66,   67,   60,   41,   95,   94,   96,
};
final static short yysindex[] = {                      -185,
 -260, -230,    0,    0, -185,    0,    0, -191, -203, -213,
    0, -187, -230,    0,    0, -173,    0, -155, -149, -154,
 -195,    0,    0,    0, -169, -151,    0, -195,    0, -141,
 -195, -150, -148,    0,    0, -173, -155, -161, -251, -168,
 -146,    0,    0,    0,    0,    0,    0,    0, -161, -144,
    0, -161,    0, -135, -139,    0,    0,    0, -217, -143,
 -152, -152, -235, -261, -128,    0,    0, -168,    0, -223,
 -161, -161, -238,    0,    0, -179, -140, -161, -168, -249,
 -168, -214,    0, -139,    0,    0,    0, -135, -128, -132,
    0,    0,    0,    0, -249, -249,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  123,    0,    0,    0, -138,    0,
    0, -131,    0,    0,    0,    0,    0,    0, -137,    0,
 -129,    0,    0,    0,    0,    0,    0, -129,    0,    0,
 -129,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -228,
    0,    0,    0, -136, -210,    0,    0,    0, -133,    0,
    0,    0,    0,    0, -186,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -205,    0,    0,    0, -163, -158, -189,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  132,    0,    8,    0,  127,  -14,    0,  105,    0,    0,
   23,    0,    3,    0,  -21,    0,    0,  -37,   71,  -46,
   75,   65,   64,   54,   73,    0,    0,    0,    0,
};
final static int YYTABLESIZE=146;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   54,   59,   69,   22,    8,   49,   33,    6,   79,   33,
   80,   21,    6,   50,   70,   25,   26,   51,   49,   52,
   57,   71,   48,   58,   76,   85,   50,   27,   31,   31,
   51,   31,   52,   71,    9,   59,   58,   78,   31,   71,
   88,   31,   73,   31,   31,   31,   27,   27,   83,   27,
   42,   28,   28,   44,   28,   79,   13,   92,   33,   27,
   14,   27,   27,   27,   28,   21,   28,   28,   28,   25,
   26,   55,   55,   33,   33,   55,   55,   71,    1,   12,
    2,   27,   90,   36,   15,   36,   55,   55,   49,   38,
    2,   19,   83,   78,   42,   49,   50,   97,   98,   61,
   51,   39,   62,   50,   49,   21,   42,   51,   42,   52,
   23,   37,   50,   37,   75,   77,   51,   24,   62,   40,
   43,   71,    3,   45,   68,   46,   39,   72,   74,   81,
   93,   87,   20,   11,   17,    8,   11,   26,   47,   17,
   47,   84,   82,   89,   91,   86,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         21,
   38,   39,   49,   18,  265,  257,   28,    0,  270,   31,
  272,  261,    5,  265,   52,  265,  266,  269,  257,  271,
  272,  257,   37,  275,   62,   72,  265,  277,  257,  258,
  269,  260,  271,  257,  265,   73,  275,  273,  267,  257,
   78,  270,  260,  272,  273,  274,  257,  258,  272,  260,
   28,  257,  258,   31,  260,  270,  260,  272,   80,  270,
  274,  272,  273,  274,  270,  261,  272,  273,  274,  265,
  266,  261,  262,   95,   96,  265,  266,  257,  264,  271,
  276,  277,   80,  270,  272,  272,  276,  277,  257,  259,
  276,  265,  272,  273,  258,  257,  265,   95,   96,  268,
  269,  271,  271,  265,  257,  261,  270,  269,  272,  271,
  260,  270,  265,  272,   61,   62,  269,  272,  271,  271,
  262,  257,    0,  274,  271,  274,  271,  267,  272,  258,
  263,  272,  262,  265,  272,  274,    5,  274,  272,   13,
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
"$$6 :",
"while : WHILE $$5 PARENL bexpr PARENR $$6 stmt",
"if : IF PARENL bexpr PARENR stmt elsepart",
"$$7 :",
"elsepart : ELSE $$7 stmt",
"elsepart :",
};

//#line 315 "project7.java"

//##############################################################################

    public int stmtCount;
    public int funcCount;

    public SymbolTable currTable;
    public SymbolTable globalTable;

    public LinkedList<String> whileLabelStack;
    public LinkedList<String> elseLabelStack;
    public LinkedList<String> branchAlwaysStack;
    public LinkedList<String> tempStack;
    public LinkedList<String> varStack;

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
    branchAlwaysStack = new LinkedList<String>();
    tempStack = new LinkedList<String>();
    varStack = new LinkedList<String>();
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
//#line 427 "Parser.java"
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
//#line 38 "project7.java"
{
    System.out.println(globalTable);
    System.out.println("The program is correct, and contains:");
    System.out.printf("%5d statements\n",stmtCount);
    System.out.printf("%5d function definitions\n",funcCount);
}
break;
case 5:
//#line 51 "project7.java"
{funcCount++;}
break;
case 7:
//#line 58 "project7.java"
{
          /*add variable to variable "stack"*/
          varStack.addLast(val_peek(2).sval);
      }
break;
case 8:
//#line 63 "project7.java"
{
          /*add variable to variable stack*/
        varStack.addLast(val_peek(0).sval);
      }
break;
case 9:
//#line 70 "project7.java"
{
              globalTable.add(val_peek(2).sval, "function");
                /*add label for new funtion*/
              ICode stmt = new ICode("NOP");
              stmt.addLabel(val_peek(2).sval);
              stmt.emit();
              currTable = new SymbolTable(val_peek(2).sval);
            }
break;
case 10:
//#line 79 "project7.java"
{
              System.out.println(currTable);
            }
break;
case 11:
//#line 82 "project7.java"
{currTable = new SymbolTable(val_peek(1).sval);}
break;
case 12:
//#line 83 "project7.java"
{
        globalTable.add(val_peek(4).sval, "function");
          /*add label for new function*/
        ICode stmt = new ICode("NOP");
        stmt.addLabel(val_peek(4).sval);
        stmt.emit();
      }
break;
case 13:
//#line 91 "project7.java"
{
        ICode returnOp = new ICode("RET");
        returnOp.emit();
        for(int x = 0; x < varStack.size(); x++){
          currTable.add(varStack.get(x), "int");
        }
        varStack.clear();
        System.out.println(currTable);
      }
break;
case 15:
//#line 105 "project7.java"
{currTable.add(val_peek(1).sval, "int");}
break;
case 17:
//#line 106 "project7.java"
{currTable.add(val_peek(0).sval, "int");}
break;
case 21:
//#line 114 "project7.java"
{stmtCount++;}
break;
case 22:
//#line 115 "project7.java"
{stmtCount++;}
break;
case 23:
//#line 116 "project7.java"
{stmtCount++;}
break;
case 24:
//#line 117 "project7.java"
{stmtCount++;}
break;
case 26:
//#line 122 "project7.java"
{
          if(tempStack.size() == 0){
            ICode assignStmt = new ICode("MOV", val_peek(0).sval, val_peek(2).sval);
            assignStmt.emit();
          }
          else{
            ICode assignStmt = new ICode("MOV", tempStack.pop(), val_peek(2).sval);
            assignStmt.emit();
          }
        }
break;
case 28:
//#line 136 "project7.java"
{
        String tempAddOp = ICode.genTemp();
        currTable.add(tempAddOp, "int");
        tempStack.push(tempAddOp);
        if(val_peek(1).sval.equals("-")){
          ICode subt = new ICode("SUB", val_peek(2).sval, val_peek(0).sval, tempAddOp);
          subt.emit();
        }
        else if(val_peek(1).sval.equals("+")){
          ICode add = new ICode("ADD", val_peek(2).sval, val_peek(0).sval, tempAddOp);
          add.emit();
        }
      }
break;
case 31:
//#line 155 "project7.java"
{yyval.sval = val_peek(0).sval;}
break;
case 32:
//#line 157 "project7.java"
{
        String numberString = String.format("%d", val_peek(0).ival);
        yyval.sval = numberString;
      }
break;
case 33:
//#line 161 "project7.java"
{yyval.sval = val_peek(1).sval;}
break;
case 34:
//#line 163 "project7.java"
{
          /*handle negative numbers*/
        String negTemp = ICode.genTemp();
        tempStack.add(negTemp);
        currTable.add(negTemp, "int");
        String tempNumber = String.format("%d", val_peek(0).ival);
        ICode negValue = new ICode("NEG", tempNumber, negTemp);
        negValue.emit();
      }
break;
case 42:
//#line 188 "project7.java"
{
        String temp, temp2;
        String newTemp = ICode.genTemp();
        currTable.add(newTemp, "int");

        if(val_peek(1).sval.equals("<")){
          ICode lessThan = new ICode("LT", val_peek(2).sval, val_peek(0).sval, newTemp);
          lessThan.emit();
        }

        else if(val_peek(1).sval.equals(">")){
          ICode greaterThan = new ICode("GT", val_peek(2).sval, val_peek(0).sval, newTemp);
          greaterThan.emit();
        }

        else if(val_peek(1).sval.equals("<=")){
          ICode lessThanEqual = new ICode("LE", val_peek(2).sval, val_peek(0).sval, newTemp);
          lessThanEqual.emit();
        }

        else if(val_peek(1).sval.equals(">=")){
          ICode greaterThanEqual = new ICode("GE", val_peek(2).sval, val_peek(0).sval, newTemp);
          greaterThanEqual.emit();
        }

        else if(val_peek(1).sval.equals("==")){
          ICode equalTo = new ICode("EQ", val_peek(2).sval, val_peek(0).sval, newTemp);
          equalTo.emit();
        }

        ICode compare = new ICode("CMP", newTemp, "0");
        compare.emit();
        String newLabel = ICode.genLabel();
        elseLabelStack.push(newLabel);
        ICode branchOnEqual = new ICode("BE", newLabel);
        branchOnEqual.emit();
      }
break;
case 44:
//#line 229 "project7.java"
{
        ICode callSimpleFunction = new ICode("CALL", val_peek(2).sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();
      }
break;
case 45:
//#line 238 "project7.java"
{
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        if(tempStack.size() == 0){
          ICode fCallParameters = new ICode("PARAM", val_peek(1).sval);
          fCallParameters.emit();
        }
        else{
          ICode fCallParameters = new ICode("PARAM", tempStack.pop());
          tempStack.push(stretTemp);
          fCallParameters.emit();
        }
        ICode callSimpleFunction = new ICode("CALL", val_peek(3).sval);
        callSimpleFunction.emit();
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();
      }
break;
case 49:
//#line 264 "project7.java"
{
        String topLabel = ICode.genLabel();
        ICode topStatement = new ICode("NOP");
        topStatement.addLabel(topLabel);
        topStatement.emit();
        whileLabelStack.push(topLabel);
      }
break;
case 50:
//#line 272 "project7.java"
{
        /*ICode cmpPart = new ICode("CMP", $4.sval, "0");*/
        ICode cmpPart = new ICode("CMP", "TEMP_FOR_BEXPR", "0");
        cmpPart.emit();
        String outLabel = ICode.genLabel();
        ICode beqPart = new ICode("BEQ", outLabel);
        beqPart.emit();
        whileLabelStack.push(outLabel);
      }
break;
case 51:
//#line 282 "project7.java"
{
        String outLabel = whileLabelStack.pop();
        String topLabel = whileLabelStack.pop();
        /*ICode backToTop = new ICode("BA", topLabel);*/
        /*/backToTop.emit();*/
        ICode whileOut = new ICode("NOP");
        whileOut.addLabel(outLabel);
        whileOut.emit();
      }
break;
case 53:
//#line 297 "project7.java"
{
            String branchAlwaysIfLbl = ICode.genLabel();
            branchAlwaysStack.push(branchAlwaysIfLbl);
            ICode branchAlwaysIf = new ICode("BA", branchAlwaysIfLbl);
            branchAlwaysIf.emit();
            String elseLabel = elseLabelStack.pop();
            ICode elseBranch = new ICode("NOP");
            elseBranch.addLabel(elseLabel);
            elseBranch.emit();
          }
break;
case 54:
//#line 308 "project7.java"
{
            ICode afterElse = new ICode("NOP");
            afterElse.addLabel(branchAlwaysStack.pop());
            afterElse.emit();
          }
break;
//#line 852 "Parser.java"
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
