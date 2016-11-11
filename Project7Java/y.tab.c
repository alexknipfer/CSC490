#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "project7.java"
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
#line 10 "y.tab.c"
#define ADDOP 257
#define AND 258
#define ASSIGNOP 259
#define COMMA 260
#define CURLL 261
#define CURLR 262
#define ELSE 263
#define FUNCTION 264
#define ID 265
#define IF 266
#define MULOP 267
#define NOT 268
#define NUMBER 269
#define OR 270
#define PARENL 271
#define PARENR 272
#define RELOP 273
#define SEMICOLON 274
#define STRING 275
#define VAR 276
#define WHILE 277
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    2,    2,    3,    5,    5,    4,    8,
    4,    6,    7,    7,    9,    9,    9,   10,   10,   10,
   10,   10,   11,   15,   15,   16,   16,   17,   17,   17,
   17,   17,   18,   18,   19,   19,   20,   20,   21,   21,
   12,   12,   22,   22,   22,   13,   14,   23,   23,
};
short yylen[] = {                                         2,
    1,    2,    1,    1,    1,    3,    3,    1,    5,    0,
    7,    3,    3,    1,    2,    2,    0,    2,    2,    1,
    1,    1,    3,    1,    3,    1,    3,    1,    1,    3,
    2,    1,    1,    3,    1,    3,    1,    2,    3,    3,
    3,    4,    3,    1,    1,    5,    6,    2,    0,
};
short yydefred[] = {                                      0,
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
short yydgoto[] = {                                       3,
    4,    5,   27,    7,   10,   28,   17,   35,   29,   30,
   31,   50,   33,   34,   60,   52,   53,   61,   62,   63,
   64,   57,   91,
};
short yysindex[] = {                                   -220,
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
short yyrindex[] = {                                      0,
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
short yygindex[] = {                                      0,
  134,    0,   11,    0,  127,  -12,  122,    0,   84,  -41,
    0,  -20,    0,    0,  -35,   74,  -43,  104,   68,   67,
  -50,   76,    0,
};
#define YYTABLESIZE 146
short yytable[] = {                                      32,
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
short yycheck[] = {                                      20,
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
#define YYFINAL 3
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 277
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"ADDOP","AND","ASSIGNOP","COMMA",
"CURLL","CURLR","ELSE","FUNCTION","ID","IF","MULOP","NOT","NUMBER","OR",
"PARENL","PARENR","RELOP","SEMICOLON","STRING","VAR","WHILE",
};
char *yyrule[] = {
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
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 167 "project7.java"

//##############################################################################

    public int stmtCount;
    public int funcCount;

    //public SymbolTable currTable;
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
#line 341 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 37 "project7.java"
{
    System.out.println("The program is correct, and contains:");
    System.out.printf("%5d statements\n",stmtCount);
    System.out.printf("%5d function definitions\n",funcCount);
}
break;
case 5:
#line 49 "project7.java"
{funcCount++;}
break;
case 7:
#line 56 "project7.java"
{
        /*LinkedList<String> vList = (LinkedList<String>) $3.obj;*/
        /*vList.add($1.sval);*/
        /*$$.obj = vList;*/
      }
break;
case 9:
#line 65 "project7.java"
{
                /*add label for new funtion*/
              ICode stmt = new ICode("NOP");
              stmt.addLabel(yyvsp[-3].sval);
              stmt.emit();
            }
break;
case 10:
#line 72 "project7.java"
{
          /*add label for new function*/
        ICode stmt = new ICode("NOP");
        stmt.addLabel(yyvsp[-3].sval);
        stmt.emit();
        /*currTable = new SymbolTable($2.sval);*/
      }
break;
case 11:
#line 80 "project7.java"
{
        /*System.out.println(currTable);*/
      }
break;
case 18:
#line 97 "project7.java"
{stmtCount++;}
break;
case 19:
#line 98 "project7.java"
{stmtCount++;}
break;
case 20:
#line 99 "project7.java"
{stmtCount++;}
break;
case 21:
#line 100 "project7.java"
{stmtCount++;}
break;
case 28:
#line 115 "project7.java"
{yyval.sval = yyvsp[0].sval;}
break;
case 29:
#line 117 "project7.java"
{
        /*String newTemp = ICode.genTemp();*/
        /*String numberString = String.format("%d", $1.ival);*/
        /*ICode stmt = new ICode("MOV", numberString, newTemp);*/
        /*stmt.emit();*/
        /*currTable.add(newTemp, "int");*/
      }
break;
case 41:
#line 146 "project7.java"
{
        /*ICode s = new ICode("Call" + $1.sval);*/
        /*s.emit();*/
      }
break;
#line 563 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
