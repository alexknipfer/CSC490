#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "project3.by"
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
    0,    1,    1,    2,    2,    3,    5,    5,    4,    4,
    6,    7,    7,    8,    8,    8,    9,    9,    9,    9,
    9,   10,   14,   14,   15,   15,   16,   16,   16,   16,
   16,   17,   17,   18,   18,   19,   19,   20,   20,   11,
   11,   21,   21,   21,   12,   13,   22,   22,
};
short yylen[] = {                                         2,
    1,    2,    1,    1,    1,    3,    3,    1,    5,    6,
    3,    3,    1,    2,    2,    0,    2,    2,    1,    1,
    1,    3,    1,    3,    1,    3,    1,    1,    3,    2,
    1,    1,    3,    1,    3,    1,    2,    3,    3,    3,
    4,    3,    1,    1,    5,    6,    2,    0,
};
short yydefred[] = {                                      0,
    0,    0,    0,    1,    0,    4,    5,    0,    0,    0,
    2,    0,    0,    6,    0,    0,    0,    7,    0,    0,
    9,    0,   12,    0,    0,    0,    0,   21,    0,    0,
    0,    0,   19,   20,   10,    0,    0,    0,    0,   14,
   11,   15,   17,   18,    0,    0,   28,    0,   31,    0,
    0,   25,   40,   44,    0,    0,    0,    0,    0,    0,
    0,   34,   36,    0,   30,    0,    0,    0,    0,   41,
   37,    0,    0,    0,    0,    0,    0,    0,   29,    0,
   26,   42,   39,    0,    0,    0,   35,   45,    0,   46,
   47,
};
short yydgoto[] = {                                       3,
    4,    5,   27,    7,   10,   28,   17,   29,   30,   31,
   49,   33,   34,   59,   51,   52,   60,   61,   62,   63,
   56,   90,
};
short yysindex[] = {                                   -226,
 -251, -233,    0,    0, -226,    0,    0, -235, -200, -209,
    0, -192, -233,    0, -181, -177, -171,    0, -169, -189,
    0, -177,    0, -214, -149, -148, -189,    0, -145, -189,
 -150, -147,    0,    0,    0, -167, -253, -176, -176,    0,
    0,    0,    0,    0, -167, -146,    0, -167,    0, -131,
 -139,    0,    0,    0, -160, -143, -166, -166, -249, -161,
 -128,    0,    0, -158,    0, -224, -167, -167, -240,    0,
    0, -252, -141, -167, -176, -183, -176, -183,    0, -139,
    0,    0,    0, -131, -128, -130,    0,    0, -183,    0,
    0,
};
short yyrindex[] = {                                      0,
    0,    0,    0,    0,  132,    0,    0,    0, -140,    0,
    0,    0,    0,    0, -137,    0,    0,    0,    0, -126,
    0,    0,    0,    0,    0,    0, -126,    0,    0, -126,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -230,    0,    0,    0, -136,
 -211,    0,    0,    0, -135,    0,    0,    0,    0,    0,
 -157,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -206,
    0,    0,    0, -217, -154, -191,    0,    0,    0,    0,
    0,
};
short yygindex[] = {                                      0,
  134,    0,    6,    0,  127,   -7,  122,   80,   30,    0,
  -20,    0,    0,  -35,   75,  -42,  104,   69,   68,   63,
   77,    0,
};
#define YYTABLESIZE 146
short yytable[] = {                                      32,
   50,   55,   65,   45,   67,    6,   32,   67,   21,   32,
    6,   46,   66,    8,   35,   47,   45,   48,   53,   79,
   74,   54,   72,   74,   46,   81,   27,   27,   47,   27,
   48,    9,   67,   55,   54,   12,   27,    1,   84,   27,
   38,   27,   27,   27,   36,   23,   23,   79,   23,    2,
   24,   24,   38,   24,   38,   32,   37,   32,   23,   13,
   23,   23,   23,   24,   14,   24,   24,   24,   32,   48,
   48,   20,   15,   48,   48,   24,   25,   20,   19,   16,
   45,   24,   25,   20,   48,   48,    2,   26,   46,   45,
   45,   57,   47,   26,   58,   15,   67,   46,   46,   69,
   22,   47,   47,   48,   58,   86,   40,   88,   75,   42,
   76,   75,   32,   78,   32,   33,   41,   33,   91,   71,
   73,   38,   39,   43,   37,   67,   44,   68,   70,   77,
   83,    3,   89,    8,   13,   16,   43,   22,   11,   18,
   23,   80,   64,   85,   87,   82,
};
short yycheck[] = {                                      20,
   36,   37,   45,  257,  257,    0,   27,  257,   16,   30,
    5,  265,   48,  265,   22,  269,  257,  271,  272,  272,
  273,  275,   58,  273,  265,   68,  257,  258,  269,  260,
  271,  265,  257,   69,  275,  271,  267,  264,   74,  270,
  258,  272,  273,  274,  259,  257,  258,  272,  260,  276,
  257,  258,  270,  260,  272,   76,  271,   78,  270,  260,
  272,  273,  274,  270,  274,  272,  273,  274,   89,  261,
  262,  261,  265,  265,  266,  265,  266,  261,  260,  272,
  257,  265,  266,  261,  276,  277,  276,  277,  265,  257,
  257,  268,  269,  277,  271,  265,  257,  265,  265,  260,
  272,  269,  269,  271,  271,   76,   27,   78,  270,   30,
  272,  270,  270,  272,  272,  270,  262,  272,   89,   57,
   58,  271,  271,  274,  271,  257,  274,  267,  272,  258,
  272,    0,  263,  274,  272,  262,  272,  274,    5,   13,
   19,   67,   39,   75,   77,   69,
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
"function : FUNCTION ID PARENL fplist PARENR body",
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
#line 149 "project3.by"

//##############################################################################

    public int stmtCount;
    public int funcCount;
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
}

//##############################################################################

public static void main(String args[])
{
 Parser par = new Parser(false);
 par.setup(args[0]);
 par.yyparse();
}
#line 333 "y.tab.c"
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
#line 37 "project3.by"
{
    System.out.println("The program is correct, and contains:");
    System.out.printf("%5d statements\n",stmtCount);
    System.out.printf("%5d function definitions\n",funcCount);
}
break;
case 5:
#line 49 "project3.by"
{funcCount++;}
break;
case 7:
#line 56 "project3.by"
{
        /*LinkedList<String> vList = (LinkedList<String>) $3.obj;*/
        /*vList.add($1.sval);*/
        /*$$.obj = vList;*/
      }
break;
case 17:
#line 80 "project3.by"
{stmtCount++;}
break;
case 18:
#line 81 "project3.by"
{stmtCount++;}
break;
case 19:
#line 82 "project3.by"
{stmtCount++;}
break;
case 20:
#line 83 "project3.by"
{stmtCount++;}
break;
case 28:
#line 100 "project3.by"
{
        String newTemp = ICode.genTemp();
        String numberString = String.format("%d", yyvsp[0].ival);
        ICode stmt = new ICode("MOV", yyvsp[0].sval, newTemp);
        stmt.emit();
      }
break;
case 40:
#line 128 "project3.by"
{
        /*ICode s = new ICode("Call" + $1.sval);*/
        /*s.emit();*/
      }
break;
#line 525 "y.tab.c"
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
