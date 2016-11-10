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
import java.util.StringTokenizer;
//#line 21 "Parser.java"




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
    4,    6,    7,    7,    9,    9,    9,   10,   10,   10,
   10,   10,   11,   15,   15,   16,   16,   17,   17,   17,
   17,   17,   18,   18,   19,   19,   20,   20,   21,   21,
   12,   12,   22,   22,   22,   13,   14,   23,   23,
};
final static short yylen[] = {                            2,
    1,    2,    1,    1,    1,    3,    3,    1,    5,    0,
    7,    3,    3,    1,    2,    2,    0,    2,    2,    1,
    1,    1,    3,    1,    3,    1,    3,    1,    1,    3,
    2,    1,    1,    3,    1,    3,    1,    2,    3,    3,
    3,    4,    3,    1,    1,    5,    6,    2,    0,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    1,    0,    4,    5,    0,    0,    0,
    2,    0,    0,    6,    0,    0,    0,    7,    0,    0,
    9,   10,   13,    0,    0,    0,    0,   22,    0,    0,
    0,    0,   20,   21,    0,    0,    0,    0,    0,   15,
   12,   16,   18,   19,   11,    0,    0,   29,    0,   32,
    0,    0,   26,   41,   45,    0,    0,    0,    0,    0,
    0,    0,   35,   37,    0,   31,    0,    0,    0,    0,
   42,   38,    0,    0,    0,    0,    0,    0,    0,   30,
    0,   27,   43,   40,    0,    0,    0,   36,   46,    0,
   47,   48,
};
final static short yydgoto[] = {                          3,
    4,    5,   27,    7,   10,   28,   17,   35,   29,   30,
   31,   50,   33,   34,   60,   52,   53,   61,   62,   63,
   64,   57,   91,
};
final static short yysindex[] = {                      -220,
 -250, -246,    0,    0, -220,    0,    0, -229, -208, -210,
    0, -260, -246,    0, -179, -181, -151,    0, -174, -190,
    0,    0,    0, -177, -186, -167, -190,    0, -139, -190,
 -149, -148,    0,    0, -181, -168, -244, -169, -169,    0,
    0,    0,    0,    0,    0, -168, -147,    0, -168,    0,
 -130, -138,    0,    0,    0, -152, -144, -159, -159, -251,
 -157, -128,    0,    0, -154,    0, -218, -168, -168, -228,
    0,    0, -180, -141, -168, -169, -187, -169, -187,    0,
 -138,    0,    0,    0, -130, -128, -131,    0,    0, -187,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  133,    0,    0,    0, -140,    0,
    0,    0,    0,    0, -137,    0,    0,    0,    0, -126,
    0,    0,    0,    0,    0,    0, -126,    0,    0, -126,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -212,    0,    0,    0,
 -136, -240,    0,    0,    0, -135,    0,    0,    0,    0,
    0, -153,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -207,    0,    0,    0, -163, -150, -193,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  134,    0,   11,    0,  127,  -12,  122,    0,   84,  -41,
    0,  -20,    0,    0,  -35,   74,  -43,  104,   68,   67,
  -50,   76,    0,
};
final static int YYTABLESIZE=146;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         32,
   51,   56,   66,   21,   15,   68,   32,   72,   74,   32,
    6,   16,   46,   67,    8,    6,   24,   24,    9,   24,
   47,   75,   45,   73,   48,   82,   49,   54,   46,   24,
   55,   24,   24,   24,   56,   87,   47,   89,   68,   85,
   48,   12,   49,    1,   28,   28,   55,   28,   92,   25,
   25,   13,   25,   80,   28,    2,   32,   28,   32,   28,
   28,   28,   25,   14,   25,   25,   25,   49,   49,   32,
   20,   49,   49,   20,   24,   25,   68,   24,   25,   20,
   19,   36,   49,   49,   38,    2,   26,   46,   46,   26,
   15,   80,   75,   37,   39,   47,   47,   46,   58,   48,
   48,   59,   49,   39,   68,   47,   39,   70,   39,   48,
   40,   59,   76,   42,   77,   76,   33,   79,   33,   34,
   22,   34,   41,   37,   43,   44,   68,   71,   69,   78,
   84,   90,    3,    8,   14,   17,   44,   23,   11,   18,
   23,   81,   65,   86,   88,   83,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         20,
   36,   37,   46,   16,  265,  257,   27,   58,   59,   30,
    0,  272,  257,   49,  265,    5,  257,  258,  265,  260,
  265,  273,   35,   59,  269,   69,  271,  272,  257,  270,
  275,  272,  273,  274,   70,   77,  265,   79,  257,   75,
  269,  271,  271,  264,  257,  258,  275,  260,   90,  257,
  258,  260,  260,  272,  267,  276,   77,  270,   79,  272,
  273,  274,  270,  274,  272,  273,  274,  261,  262,   90,
  261,  265,  266,  261,  265,  266,  257,  265,  266,  261,
  260,  259,  276,  277,  271,  276,  277,  257,  257,  277,
  265,  272,  273,  271,  258,  265,  265,  257,  268,  269,
  269,  271,  271,  271,  257,  265,  270,  260,  272,  269,
   27,  271,  270,   30,  272,  270,  270,  272,  272,  270,
  272,  272,  262,  271,  274,  274,  257,  272,  267,  258,
  272,  263,    0,  274,  272,  262,  272,  274,    5,   13,
   19,   68,   39,   76,   78,   70,
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
"function : FUNCTION ID PARENL fplist PARENR $$1 body",
"body : CURLL bodylist CURLR",
"fplist : ID COMMA fplist",
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
"while : WHILE PARENL bexpr PARENR stmt",
"if : IF PARENL bexpr PARENR stmt elsepart",
"elsepart : ELSE stmt",
"elsepart :",
};

//#line 165 "project7.java"

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
    stmtCount=0;
    funcCount=0;

    globalTable = new SymbolTable("__GLOBAL__");
}

//##############################################################################

public static void main(String args[])
{
 Parser par = new Parser(false);
 par.setup(args[0]);
 par.yyparse();
}
//#line 405 "Parser.java"
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
//#line 37 "project7.java"
{
    System.out.println("The program is correct, and contains:");
    System.out.printf("%5d statements\n",stmtCount);
    System.out.printf("%5d function definitions\n",funcCount);
}
break;
case 5:
//#line 49 "project7.java"
{funcCount++;}
break;
case 7:
//#line 56 "project7.java"
{
        /*LinkedList<String> vList = (LinkedList<String>) $3.obj;*/
        /*vList.add($1.sval);*/
        /*$$.obj = vList;*/
      }
break;
case 9:
//#line 65 "project7.java"
{
              ICode stmt = new ICode("NOP");
              stmt.addLabel(val_peek(3).sval);
              stmt.emit();
            }
break;
case 10:
//#line 71 "project7.java"
{
        ICode stmt = new ICode("NOP");
        stmt.addLabel(val_peek(3).sval);
        stmt.emit();
        currTable = new SymbolTable(val_peek(3).sval);
      }
break;
case 11:
//#line 78 "project7.java"
{
        System.out.println(currTable);
      }
break;
case 18:
//#line 95 "project7.java"
{stmtCount++;}
break;
case 19:
//#line 96 "project7.java"
{stmtCount++;}
break;
case 20:
//#line 97 "project7.java"
{stmtCount++;}
break;
case 21:
//#line 98 "project7.java"
{stmtCount++;}
break;
case 28:
//#line 113 "project7.java"
{yyval.sval = val_peek(0).sval;}
break;
case 29:
//#line 115 "project7.java"
{
        String newTemp = ICode.genTemp();
        String numberString = String.format("%d", val_peek(0).ival);
        ICode stmt = new ICode("MOV", numberString, newTemp);
        stmt.emit();
        currTable.add(newTemp, "int");
      }
break;
case 41:
//#line 144 "project7.java"
{
        /*ICode s = new ICode("Call" + $1.sval);*/
        /*s.emit();*/
      }
break;
//#line 634 "Parser.java"
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
