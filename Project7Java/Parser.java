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
    0,    1,    1,    2,    2,    3,    5,    5,    4,    8,
    9,    4,    6,   11,    7,    7,   10,   10,   10,   12,
   12,   12,   12,   12,   13,   17,   17,   18,   18,   19,
   19,   19,   19,   19,   20,   20,   21,   21,   22,   22,
   23,   23,   14,   14,   24,   24,   24,   25,   26,   15,
   28,   16,   29,   27,   27,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    5,    0,
    0,    8,    3,    0,    4,    1,    2,    2,    0,    2,
    2,    1,    1,    1,    3,    1,    3,    1,    3,    1,
    1,    3,    2,    1,    1,    3,    1,    3,    1,    2,
    3,    3,    3,    4,    3,    1,    1,    0,    0,    7,
    0,    7,    0,    3,    0,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    1,    0,    4,    5,    0,    0,    0,
    2,    0,    0,    6,    0,    0,    7,    0,    9,    0,
    0,    0,    0,   48,    0,   24,    0,    0,    0,    0,
   22,   23,   14,   11,    0,    0,    0,    0,   17,   13,
   18,   20,   21,    0,    0,    0,    0,   31,    0,   34,
    0,    0,   28,   43,   47,    0,    0,    0,    0,    0,
    0,    0,   37,   39,    0,   15,   12,   33,    0,    0,
    0,    0,   44,   40,    0,    0,    0,    0,    0,    0,
    0,   32,    0,   29,   45,   42,    0,    0,   51,   38,
   49,    0,    0,   53,   52,   50,    0,   54,
};
final static short yydgoto[] = {                          3,
    4,    5,   25,    7,   10,   26,   21,   16,   45,   27,
   44,   28,   29,   50,   31,   32,   60,   52,   53,   61,
   62,   63,   64,   57,   38,   93,   95,   92,   97,
};
final static short yysindex[] = {                      -163,
 -254, -247,    0,    0, -163,    0,    0, -244, -229, -219,
    0, -227, -247,    0, -202, -197,    0, -194,    0, -186,
 -184, -154, -148,    0, -194,    0, -151, -194, -159, -155,
    0,    0,    0,    0, -171, -252, -172, -147,    0,    0,
    0,    0,    0, -197, -202, -171, -146,    0, -171,    0,
 -131, -140,    0,    0,    0, -248, -144, -162, -162, -249,
 -164, -129,    0,    0, -172,    0,    0,    0, -251, -171,
 -171, -243,    0,    0, -181, -142, -171, -172, -188, -172,
 -160,    0, -140,    0,    0,    0, -131, -129,    0,    0,
    0, -132, -188,    0,    0,    0, -188,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  132,    0,    0,    0, -141,    0,
    0, -130,    0,    0,    0,    0,    0, -128,    0, -136,
    0,    0,    0,    0, -128,    0,    0, -128,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -221,    0,    0,    0,
 -137, -216,    0,    0,    0, -134,    0,    0,    0,    0,
    0, -156,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -210,    0,    0,    0, -168, -152,    0,    0,
    0, -196,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  134,    0,   35,    0,  127,  -12,   97,    0,    0,   59,
    0,  -63,    0,  -18,    0,    0,  -34,   72,  -42,   78,
   66,   65,   63,   74,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=146;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   51,   56,   19,   68,   46,   70,   30,   70,   70,   30,
    8,   72,   47,   46,   69,   89,   48,    9,   49,   54,
   82,   47,   55,   77,   75,   48,   12,   49,   84,   96,
   13,   55,   67,   98,    6,   30,   30,   56,   30,    6,
   26,   26,   87,   26,   15,   30,   27,   27,   30,   27,
   30,   30,   30,   26,   14,   26,   26,   26,   18,   27,
   30,   27,   27,   27,   55,   55,   18,   20,   55,   55,
   22,   23,   18,   33,   30,   70,   22,   23,   30,   55,
   55,    2,   24,   39,   46,   46,   41,   34,   24,   41,
   82,   77,   47,   47,   46,   58,   48,   48,   59,   49,
    1,   41,   47,   41,   35,   78,   48,   79,   59,   78,
   40,   91,    2,   35,   42,   35,   36,   36,   43,   36,
   74,   76,   37,   65,   36,   70,   71,   73,   80,   86,
   94,    3,    8,   19,   10,   16,   25,   46,   11,   17,
   66,   83,   81,   88,   90,   85,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         18,
   35,   36,   15,   46,  257,  257,   25,  257,  257,   28,
  265,  260,  265,  257,   49,   79,  269,  265,  271,  272,
  272,  265,  275,  273,   59,  269,  271,  271,   71,   93,
  260,  275,   45,   97,    0,  257,  258,   72,  260,    5,
  257,  258,   77,  260,  272,  267,  257,  258,  270,  260,
  272,  273,  274,  270,  274,  272,  273,  274,  261,  270,
   79,  272,  273,  274,  261,  262,  261,  265,  265,  266,
  265,  266,  261,  260,   93,  257,  265,  266,   97,  276,
  277,  276,  277,   25,  257,  257,   28,  272,  277,  258,
  272,  273,  265,  265,  257,  268,  269,  269,  271,  271,
  264,  270,  265,  272,  259,  270,  269,  272,  271,  270,
  262,  272,  276,  270,  274,  272,  271,  270,  274,  272,
   58,   59,  271,  271,  271,  257,  267,  272,  258,  272,
  263,    0,  274,  262,  265,  272,  274,  272,    5,   13,
   44,   70,   65,   78,   80,   72,
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
"function : FUNCTION ID PARENL PARENR body",
"$$1 :",
"$$2 :",
"function : FUNCTION ID PARENL $$1 fplist PARENR $$2 body",
"body : CURLL bodylist CURLR",
"$$3 :",
"fplist : ID COMMA $$3 fplist",
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
"$$4 :",
"$$5 :",
"while : WHILE $$4 PARENL bexpr PARENR $$5 stmt",
"$$6 :",
"if : IF PARENL bexpr PARENR stmt $$6 elsepart",
"$$7 :",
"elsepart : ELSE $$7 stmt",
"elsepart :",
};

//#line 297 "project7.java"

//##############################################################################

    public int stmtCount;
    public int funcCount;

    public SymbolTable currTable;
    public SymbolTable globalTable;

    public LinkedList<String> whileLabelStack;
    public LinkedList<String> elseLabelStack;
    public LinkedList<String> branchAlwaysStack;
    public LinkedList<String> tempStack;

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
//#line 425 "Parser.java"
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
    System.out.println("The program is correct, and contains:");
    System.out.printf("%5d statements\n",stmtCount);
    System.out.printf("%5d function definitions\n",funcCount);
}
break;
case 5:
//#line 50 "project7.java"
{funcCount++;}
break;
case 7:
//#line 57 "project7.java"
{
        /*LinkedList<String> vList = (LinkedList<String>) $3.obj;*/
        /*vList.add($1.sval);*/
        /*$$.obj = vList;*/
      }
break;
case 9:
//#line 66 "project7.java"
{
                /*add label for new funtion*/
              ICode stmt = new ICode("NOP");
              stmt.addLabel(val_peek(3).sval);
              stmt.emit();
              currTable = new SymbolTable(val_peek(3).sval);
            }
break;
case 10:
//#line 73 "project7.java"
{currTable = new SymbolTable(val_peek(1).sval);}
break;
case 11:
//#line 74 "project7.java"
{
          /*add label for new function*/
        ICode stmt = new ICode("NOP");
        stmt.addLabel(val_peek(4).sval);
        stmt.emit();
      }
break;
case 12:
//#line 81 "project7.java"
{
        System.out.println(currTable);
      }
break;
case 14:
//#line 89 "project7.java"
{currTable.add(val_peek(1).sval, "int");}
break;
case 16:
//#line 90 "project7.java"
{currTable.add(val_peek(0).sval, "int");}
break;
case 20:
//#line 98 "project7.java"
{stmtCount++;}
break;
case 21:
//#line 99 "project7.java"
{stmtCount++;}
break;
case 22:
//#line 100 "project7.java"
{stmtCount++;}
break;
case 23:
//#line 101 "project7.java"
{stmtCount++;}
break;
case 25:
//#line 106 "project7.java"
{
          /*System.out.println("EXPR: " + $3.sval);*/
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
case 27:
//#line 121 "project7.java"
{
        String tempAddOp = ICode.genTemp();
        tempStack.push(tempAddOp);
        if(val_peek(1).sval.equals("-")){
          ICode subt = new ICode("SUB", val_peek(2).sval, val_peek(0).sval, tempAddOp);
          subt.emit();
        }
      }
break;
case 30:
//#line 135 "project7.java"
{yyval.sval = val_peek(0).sval;}
break;
case 31:
//#line 137 "project7.java"
{
        String numberString = String.format("%d", val_peek(0).ival);
        yyval.sval = numberString;
      }
break;
case 32:
//#line 141 "project7.java"
{yyval.sval = val_peek(1).sval;}
break;
case 34:
//#line 143 "project7.java"
{System.out.print("FUNCTION CALL");}
break;
case 41:
//#line 159 "project7.java"
{
        String temp, temp2;
        String newTemp = ICode.genTemp();
        currTable.add(newTemp, "int");
          /*check if value is an integer*/
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
case 43:
//#line 200 "project7.java"
{
        ICode callSimpleFunction = new ICode("CALL", val_peek(2).sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();
      }
break;
case 44:
//#line 209 "project7.java"
{
        ICode fCallParameters = new ICode("PARAM", val_peek(1).sval);
        fCallParameters.emit();
        ICode callSimpleFunction = new ICode("CALL", val_peek(3).sval);
        callSimpleFunction.emit();
        String stretTemp = ICode.genTemp();
        currTable.add(stretTemp, "int");
        ICode stret = new ICode("STRET", stretTemp);
        stret.emit();
      }
break;
case 48:
//#line 228 "project7.java"
{
        String topLabel = ICode.genLabel();
        ICode topStatement = new ICode("NOP");
        topStatement.addLabel(topLabel);
        topStatement.emit();
        whileLabelStack.push(topLabel);
      }
break;
case 49:
//#line 236 "project7.java"
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
case 50:
//#line 246 "project7.java"
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
case 51:
//#line 259 "project7.java"
{
      /*System.out.println($5.sval);*/
      /*String branchAlwaysIfLbl = ICode.genLabel();*/
      /*ICode branchAlwaysIf = new ICode("BA", branchAlwaysIfLbl);*/
      /*branchAlwaysIf.emit();*/
    }
break;
case 53:
//#line 269 "project7.java"
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
//#line 280 "project7.java"
{
            ICode afterElse = new ICode("NOP");
            afterElse.addLabel(branchAlwaysStack.pop());
            afterElse.emit();
            /*String branchAlwaysIfLbl = ICode.genLabel();*/
            /*ICode branchAlwaysIf = new ICode("BA", branchAlwaysIfLbl);*/
            /*branchAlwaysStack.push(branchAlwaysIfLbl);*/
            /*branchAlwaysIf.emit();*/
          }
break;
case 55:
//#line 290 "project7.java"
{
          /*String elseNothingLabel = elseLabelStack.pop();*/
          /*ICode elseNothingBranch = new ICode("NOP");*/
          /*elseNothingBranch.addLabel(elseNothingLabel);*/
          /*elseNothingBranch.emit();*/
        }
break;
//#line 832 "Parser.java"
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
