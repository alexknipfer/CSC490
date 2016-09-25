/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.3"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Using locations.  */
#define YYLSP_NEEDED 0



/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     FUNCTION = 258,
     ID = 259,
     PARENL = 260,
     PARENR = 261,
     CURLL = 262,
     CURLR = 263,
     VAR = 264,
     SEMICOLON = 265,
     COMMA = 266,
     ASSIGNOP = 267,
     STRING = 268,
     IF = 269,
     ELSE = 270,
     WHILE = 271,
     OR = 272,
     NOT = 273,
     AND = 274,
     RELOP = 275,
     ADDOP = 276,
     MULOP = 277,
     NUMBER = 278
   };
#endif
/* Tokens.  */
#define FUNCTION 258
#define ID 259
#define PARENL 260
#define PARENR 261
#define CURLL 262
#define CURLR 263
#define VAR 264
#define SEMICOLON 265
#define COMMA 266
#define ASSIGNOP 267
#define STRING 268
#define IF 269
#define ELSE 270
#define WHILE 271
#define OR 272
#define NOT 273
#define AND 274
#define RELOP 275
#define ADDOP 276
#define MULOP 277
#define NUMBER 278




/* Copy the first part of user declarations.  */
#line 1 "lex2.cpp"

  #include <cstring>
  #include <fstream>
  #include <iostream>
  #include <string>
  #include <vector>

  using namespace std;

  int yylex();
  int yyerror(const char *message){cerr << "Error!!" << endl; return 0;};



/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif

#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 16 "lex2.cpp"
{
	double dval;
	char *sval;
}
/* Line 193 of yacc.c.  */
#line 161 "lex2.tab.cpp"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 174 "lex2.tab.cpp"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int i)
#else
static int
YYID (i)
    int i;
#endif
{
  return i;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss;
  YYSTYPE yyvs;
  };

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack)					\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack, Stack, yysize);				\
	Stack = &yyptr->Stack;						\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  10
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   110

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  24
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  22
/* YYNRULES -- Number of rules.  */
#define YYNRULES  47
/* YYNRULES -- Number of states.  */
#define YYNSTATES  91

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   278

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint8 yyprhs[] =
{
       0,     0,     3,     6,     8,    10,    12,    16,    20,    22,
      28,    35,    39,    43,    45,    48,    51,    52,    55,    58,
      60,    62,    64,    68,    70,    74,    76,    80,    82,    84,
      88,    91,    93,    95,    99,   101,   105,   107,   110,   114,
     118,   122,   127,   131,   133,   135,   141,   147
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      25,     0,    -1,    26,    25,    -1,    26,    -1,    27,    -1,
      29,    -1,     9,    28,    10,    -1,     4,    11,    28,    -1,
       4,    -1,     3,     4,     5,     6,    30,    -1,     3,     4,
       5,    31,     6,    30,    -1,     7,    32,     8,    -1,     4,
      11,    31,    -1,     4,    -1,    27,    32,    -1,    33,    32,
      -1,    -1,    34,    10,    -1,    42,    10,    -1,    44,    -1,
      45,    -1,    30,    -1,     4,    12,    35,    -1,    36,    -1,
      35,    21,    36,    -1,    37,    -1,    36,    22,    37,    -1,
       4,    -1,    23,    -1,     5,    35,     6,    -1,    21,    37,
      -1,    42,    -1,    39,    -1,    38,    17,    39,    -1,    40,
      -1,    39,    19,    40,    -1,    41,    -1,    18,    41,    -1,
      35,    20,    35,    -1,     5,    41,     6,    -1,     4,     5,
       6,    -1,     4,     5,    43,     6,    -1,    35,    11,    43,
      -1,    35,    -1,    13,    -1,    16,     5,    38,     6,    33,
      -1,    14,     5,    38,     6,    33,    -1,    14,     5,    38,
       6,    33,    15,    33,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint8 yyrline[] =
{
       0,    45,    45,    45,    47,    47,    49,    51,    51,    53,
      54,    56,    58,    58,    60,    60,    60,    62,    63,    64,
      65,    66,    68,    70,    70,    72,    72,    74,    75,    76,
      77,    78,    80,    80,    82,    82,    84,    84,    86,    86,
      88,    89,    91,    92,    93,    95,    97,    98
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "FUNCTION", "ID", "PARENL", "PARENR",
  "CURLL", "CURLR", "VAR", "SEMICOLON", "COMMA", "ASSIGNOP", "STRING",
  "IF", "ELSE", "WHILE", "OR", "NOT", "AND", "RELOP", "ADDOP", "MULOP",
  "NUMBER", "$accept", "pgm", "pgmpart", "vardecl", "varlist", "function",
  "body", "fplist", "bodylist", "stmt", "assign", "expr", "factor", "term",
  "bexpr", "bfactor", "bneg", "bterm", "fcall", "aplist", "while", "if", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    24,    25,    25,    26,    26,    27,    28,    28,    29,
      29,    30,    31,    31,    32,    32,    32,    33,    33,    33,
      33,    33,    34,    35,    35,    36,    36,    37,    37,    37,
      37,    37,    38,    38,    39,    39,    40,    40,    41,    41,
      42,    42,    43,    43,    43,    44,    45,    45
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     2,     1,     1,     1,     3,     3,     1,     5,
       6,     3,     3,     1,     2,     2,     0,     2,     2,     1,
       1,     1,     3,     1,     3,     1,     3,     1,     1,     3,
       2,     1,     1,     3,     1,     3,     1,     2,     3,     3,
       3,     4,     3,     1,     1,     5,     5,     7
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     3,     4,     5,     0,     8,     0,
       1,     2,     0,     0,     6,    13,     0,     0,     7,     0,
      16,     9,     0,    12,     0,     0,     0,    16,    21,     0,
      16,     0,     0,    19,    20,    10,     0,     0,     0,     0,
      14,    11,    15,    17,    18,    27,     0,    40,    44,     0,
      28,    43,    23,    25,    31,     0,    22,     0,     0,     0,
       0,    32,    34,    36,     0,     0,    30,     0,     0,     0,
      41,     0,     0,    37,     0,     0,     0,     0,     0,    29,
      42,    24,    26,    39,    38,    46,    33,    35,    45,     0,
      47
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int8 yydefgoto[] =
{
      -1,     3,     4,    27,     9,     6,    28,    17,    29,    30,
      31,    59,    52,    53,    60,    61,    62,    63,    54,    55,
      33,    34
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -47
static const yytype_int8 yypact[] =
{
      44,    22,    58,    75,    44,   -47,   -47,    76,    68,    72,
     -47,   -47,    74,    58,   -47,    73,    78,    77,   -47,    82,
      56,   -47,    78,   -47,    39,    83,    84,    56,   -47,    79,
      56,    80,    81,   -47,   -47,   -47,     8,     4,    12,    12,
     -47,   -47,   -47,   -47,   -47,    87,     4,   -47,   -47,     4,
     -47,    55,    71,   -47,   -47,    88,    85,    36,    36,   -15,
      -2,    86,   -47,   -47,    37,    31,   -47,    15,     4,     4,
     -47,    28,    89,   -47,     4,    57,    12,    12,    57,   -47,
     -47,    71,   -47,   -47,    85,    92,    86,   -47,   -47,    57,
     -47
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int8 yypgoto[] =
{
     -47,    93,   -47,    46,    90,   -47,     2,    91,    47,   -33,
     -47,   -35,    30,   -46,    60,    20,    23,    10,   -20,    34,
     -47,   -47
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_uint8 yytable[] =
{
      32,    51,    56,    66,    75,    74,    68,    32,    45,    46,
      32,    65,    45,    46,    47,    76,    45,    57,    21,    45,
      46,    48,    71,    82,    35,    49,     7,    50,    48,    49,
      58,    50,    51,    49,    79,    50,    49,    79,    50,    84,
      45,    57,    85,    78,    36,    88,     5,     1,    74,    68,
       5,    37,    68,     2,    76,    32,    90,    49,    32,    50,
      24,    24,     8,    20,    20,     2,    67,    72,    73,    32,
      25,    25,    26,    26,    40,    10,    68,    42,    15,    13,
      16,    12,    14,    22,    19,    20,    15,    41,    38,    39,
      43,    44,    36,    69,    70,    83,    86,    11,    81,    64,
      87,    80,     0,    18,     0,    77,    68,    89,     0,     0,
      23
};

static const yytype_int8 yycheck[] =
{
      20,    36,    37,    49,     6,    20,    21,    27,     4,     5,
      30,    46,     4,     5,     6,    17,     4,     5,    16,     4,
       5,    13,    57,    69,    22,    21,     4,    23,    13,    21,
      18,    23,    67,    21,     6,    23,    21,     6,    23,    74,
       4,     5,    75,     6,     5,    78,     0,     3,    20,    21,
       4,    12,    21,     9,    17,    75,    89,    21,    78,    23,
       4,     4,     4,     7,     7,     9,    11,    57,    58,    89,
      14,    14,    16,    16,    27,     0,    21,    30,     4,    11,
       6,     5,    10,     6,    11,     7,     4,     8,     5,     5,
      10,    10,     5,    22,     6,     6,    76,     4,    68,    39,
      77,    67,    -1,    13,    -1,    19,    21,    15,    -1,    -1,
      19
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     3,     9,    25,    26,    27,    29,     4,     4,    28,
       0,    25,     5,    11,    10,     4,     6,    31,    28,    11,
       7,    30,     6,    31,     4,    14,    16,    27,    30,    32,
      33,    34,    42,    44,    45,    30,     5,    12,     5,     5,
      32,     8,    32,    10,    10,     4,     5,     6,    13,    21,
      23,    35,    36,    37,    42,    43,    35,     5,    18,    35,
      38,    39,    40,    41,    38,    35,    37,    11,    21,    22,
       6,    35,    41,    41,    20,     6,    17,    19,     6,     6,
      43,    36,    37,     6,    35,    33,    39,    40,    33,    15,
      33
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *bottom, yytype_int16 *top)
#else
static void
yy_stack_print (bottom, top)
    yytype_int16 *bottom;
    yytype_int16 *top;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; bottom <= top; ++bottom)
    YYFPRINTF (stderr, " %d", *bottom);
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      fprintf (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      fprintf (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */



/* The look-ahead symbol.  */
int yychar;

/* The semantic value of the look-ahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
  
  int yystate;
  int yyn;
  int yyresult;
  /* Number of tokens to shift before error messages enabled.  */
  int yyerrstatus;
  /* Look-ahead token as an internal (translated) token number.  */
  int yytoken = 0;
#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

  /* Three stacks and their tools:
     `yyss': related to states,
     `yyvs': related to semantic values,
     `yyls': related to locations.

     Refer to the stacks thru separate pointers, to allow yyoverflow
     to reallocate them elsewhere.  */

  /* The state stack.  */
  yytype_int16 yyssa[YYINITDEPTH];
  yytype_int16 *yyss = yyssa;
  yytype_int16 *yyssp;

  /* The semantic value stack.  */
  YYSTYPE yyvsa[YYINITDEPTH];
  YYSTYPE *yyvs = yyvsa;
  YYSTYPE *yyvsp;



#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  YYSIZE_T yystacksize = YYINITDEPTH;

  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;


  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;


	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),

		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss);
	YYSTACK_RELOCATE (yyvs);

#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;


      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     look-ahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to look-ahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a look-ahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid look-ahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the look-ahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
      
/* Line 1267 of yacc.c.  */
#line 1445 "lex2.tab.cpp"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;


  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse look-ahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse look-ahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#ifndef yyoverflow
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEOF && yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


#line 100 "lex2.cpp"


void myCopy(char* &into, const string &from)
{
	into = new char[from.length() +1];
	strcpy(into, from.c_str());
}

ifstream inputFile;
vector<char> token;
vector<string> finalTokens;
int tokenCount;
int statementCount;
int functionCount;

bool isAssignOp(char ch){
  if(ch == '<' || ch == '-'){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a whitespace
bool isWhiteSpace(char ch){
  if(ch == ' ' || ch == '\n' || ch == '\0'){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a STRING ("" quotes)
bool isString(char ch){
  if(ch == '"'){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a symbol
bool isSymbol(char ch){
  if(ch == '(' || ch == ')' || ch == ',' || ch == '{' || ch == '}' || ch == '+' ||
     ch == '-' || ch == '*' || ch == '/' || ch == '&' || ch == '!' || ch == '|' || ch == ';'){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a character of a RELOP
bool isRelop(char ch){
  if(ch == '=' || ch == '!' || ch == '<' || ch == '>'){
    return true;
  }

  else{
    return false;
  }
}

  //returns true if argument is a RELOP
bool isRelopString(string currentToken){
  if(currentToken == "==" || currentToken == "!=" || currentToken == "<" ||
          currentToken == ">" || currentToken == "<=" || currentToken == ">="){
      return true;
  }
  else{
      return false;
  }
}

  //returns true if argument is a FUNCTION keyword
bool isFunction(string currentToken){
  if(currentToken == "function"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a IF keyword
bool isIf(string currentToken){
  if(currentToken == "if"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a OR symbol
bool isOr(string currentToken){
  if(currentToken == "|"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a TRUE keyword
bool isElse(string currentToken){
  if(currentToken == "else"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a NOT symbol
bool isNot(string currentToken){
  if(currentToken == "!"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a RETURN keyword
bool isReturn(string currentToken){
  if(currentToken == "return"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a VAR keyword
bool isVar(string currentToken){
  if(currentToken == "var"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a WHILE keyword
bool isWhile(string currentToken){
  if(currentToken == "while"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a ID
bool isID(string currentToken){
  if(isalpha(currentToken[0]) && currentToken != "if" && currentToken != "function" &&
     currentToken != "else" && currentToken != "return" && currentToken != "var" &&
     currentToken != "while"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a left parentesis
bool isParenL(string currentToken){
  if(currentToken == "("){
    return true;
  }
  else{
    return false;
  }
}

//returns true if argument is a right parentesis
bool isParenR(string currentToken){
  if(currentToken == ")"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a left curly brace
bool isCurlL(string currentToken){
  if(currentToken == "{"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a right curly brace
bool isCurlR(string currentToken){
  if(currentToken == "}"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a SEMICOLON
bool isSemicolon(string currentToken){
  if(currentToken == ";"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a ADDOP (+ or -)
bool isAddOp(string currentToken){
  if(currentToken == "+" || currentToken == "-"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a MULOP (/ or *)
bool isMulOp(string currentToken){
  if(currentToken == "/" || currentToken == "*"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a comma
bool isComma(string currentToken){
  if(currentToken == ","){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a AND symbol
bool isAnd(string currentToken){
  if(currentToken == "&"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if argument is a ASSIGNMENT operator
bool isAssignmentOperator(string currentToken){
  if(currentToken == "<-"){
    return true;
  }
  else{
    return false;
  }
}

  //returns true if a argument is a COMMENT symbol (#)
bool isComment(char ch){
  if(ch == '#'){
    return true;
  }
  else{
    return false;
  }
}

string analyzeToken(vector<char> token){
  string currentToken;
  for(int x = 0; x < token.size(); x++){
    currentToken += token[x];
  }
    //checks if token is a function keyword
  if(isFunction(currentToken)){
    finalTokens.push_back("function");
  }

  else if(isAssignmentOperator(currentToken)){
    finalTokens.push_back("assignOp");
  }

    //checks if token is a if keyword
  else if(isIf(currentToken)){
    finalTokens.push_back("if");
  }

    //checks if token is a else keyword
  else if(isElse(currentToken)){
    finalTokens.push_back("else");
  }

    //checks if token is a not symbol
  else if(isNot(currentToken)){
    finalTokens.push_back("not");
  }

    //checks if token is a or keyword
  else if(isOr(currentToken)){
    finalTokens.push_back("or");
  }

    //checks if token is a VAR keyword
  else if(isVar(currentToken)){
    finalTokens.push_back("var");
  }

    //checks if token is a WHILE keyword
  else if(isWhile(currentToken)){
    finalTokens.push_back("while");
  }

  //checks if token is a semicolon
  else if(isSemicolon(currentToken)){
    finalTokens.push_back("semicolon");
  }

    //checks if token is a left parentesis
  else if(isParenL(currentToken)){
    finalTokens.push_back("parenL");
  }

  else if(isParenR(currentToken)){
    finalTokens.push_back("parenR");
  }

    //checks if token is a comma
  else if(isComma(currentToken)){
    finalTokens.push_back("comma");
  }

    //checks if token is left curly
  else if(isCurlL(currentToken)){
    finalTokens.push_back("curlL");
  }

    //checks if token is right curly
  else if(isCurlR(currentToken)){
    finalTokens.push_back("curlR");
  }

    //checks if token is a ADDOP (+ or -)
  else if(isAddOp(currentToken)){
    finalTokens.push_back("addOp");
  }

    //checks if token is a MULLOP (/ or *)
  else if(isMulOp(currentToken)){
    finalTokens.push_back("mulOp");
  }

    //checks if token is an AND
  else if(isAnd(currentToken)){
    finalTokens.push_back("and");
  }

    //checks if token is a RELOP
  else if(isRelopString(currentToken)){
    finalTokens.push_back("relop");
  }

    //checks if token is a ID
  else if(isID(currentToken)){
    finalTokens.push_back("id");
  }

  return "";
}

int main(int argc, char *argv[])
{
	inputFile.open(argv[1], ifstream::in);
  string currentToken;
  string readLine;
  char lookahead;
  string myNumber = "";
  string myString = "";
  int tempX;
  tokenCount = 0;
  statementCount = 0;
  functionCount = 0;

	if(!inputFile)
	{
		cerr << "Could not open input file:" << argv[1] << endl;
		return 1;
	}

  //continue to go through input file line by line
while(getline(inputFile, readLine)){
    //parse line to get tokens
  for(int x = 0; x < readLine.length(); x++){
    tempX = x;
    lookahead = readLine[x];

      //add character to vector to keep track of token
    token.push_back(readLine[x]);

      //analyze token since white space is read
    if(isWhiteSpace(lookahead)){
      token.pop_back();
      analyzeToken(token);
      token.clear();
    }

      //analyze token since symbol is read
      //once a symbol is reached, pop the symbol off the vector because the
      //previously a token was reached
    else if(isSymbol(lookahead)){
      token.pop_back();
      analyzeToken(token);
      token.clear();
      token.push_back(lookahead);
      analyzeToken(token);
      token.clear();
    }

      //analyze number to see if it is a integer or a part of a string
      //make sure it is a INTEGER
    else if(isdigit(lookahead) && !isalpha(readLine[x-1])){
        //keep getting integer values
      while(isdigit(readLine[x+1])){
        token.push_back(readLine[x+1]);
        x++;
      }
        //add integer to string for printing
      for(int y = 0; y < token.size(); y++){
        myNumber += token[y];
      }

      finalTokens.push_back("number");
      myNumber = "";
      token.clear();
    }

      //if a comment is reached (#), exit loop and don't print line
    else if(isComment(lookahead)){
      token.clear();
      break;
    }

        //if a " is reached the current token is a string
        //continue reading in string value
    else if(isString(lookahead)){
      token.pop_back();

        //keep reading until end of string
      while(readLine[x+1] != '"'){
        token.push_back(readLine[x+1]);
        x++;
      }

        //add token (in vector) to string for printing
      for(int y = 0; y < token.size(); y++){
        myString += token[y];
      }
      x++;

      finalTokens.push_back("string");
      myString = "";
      token.clear();
    }

      //check to see if value read in is a RELOP
    else if(isRelop(lookahead)){
      token.pop_back();
      analyzeToken(token);
      token.clear();

        //check next character to see if it's a valid RELOP
      if(isRelop(readLine[x+1])){
        token.push_back(lookahead);
        token.push_back(readLine[x+1]);
        analyzeToken(token);
        token.clear();
      }

        //check to see if next character creates an assignment operator
      else if(isAssignOp(readLine[x+1])){
        token.clear();
        token.push_back(lookahead);
        token.push_back(readLine[x+1]);
        x = x + 1;
        analyzeToken(token);
        token.clear();
      }

      else if(lookahead == '<' || lookahead == '>'){
        token.push_back(lookahead);
        analyzeToken(token);
        token.clear();
      }
    }
  }
}

	yyparse();

	return 0;
}

int yylex()
{
  if(finalTokens[tokenCount] == "var"){
    tokenCount++;
    return VAR;
  }

  else if(finalTokens[tokenCount] == "id"){
    tokenCount++;
    return ID;
  }

  else if(finalTokens[tokenCount] == "semicolon"){
    tokenCount++;
    return SEMICOLON;
  }

  else if(finalTokens[tokenCount] == "function"){
    tokenCount++;
    functionCount++;
    return FUNCTION;
  }

  else if(finalTokens[tokenCount] == "parenL"){
    tokenCount++;
    return PARENL;
  }

  else if(finalTokens[tokenCount] == "parenR"){
    tokenCount++;
    return PARENR;
  }

  else if(finalTokens[tokenCount] == "curlL"){
    tokenCount++;
    return CURLL;
  }

  else if(finalTokens[tokenCount] == "curlR"){
    tokenCount++;
    return CURLR;
  }

  else if(finalTokens[tokenCount] == "comma"){
    tokenCount++;
    return COMMA;
  }

  else if(finalTokens[tokenCount] == "assignOp"){
    tokenCount++;
    statementCount++;
    return ASSIGNOP;
  }

  else if(finalTokens[tokenCount] == "string"){
    tokenCount++;
    return STRING;
  }

  else if(finalTokens[tokenCount] == "if"){
    tokenCount++;
    return IF;
  }

  else if(finalTokens[tokenCount] == "else"){
    tokenCount++;
    return ELSE;
  }

  else if(finalTokens[tokenCount] == "while"){
    tokenCount++;
    return WHILE;
  }

  else if(finalTokens[tokenCount] == "or"){
    tokenCount++;
    return OR;
  }

  else if(finalTokens[tokenCount] == "not"){
    tokenCount++;
    return NOT;
  }

  else if(finalTokens[tokenCount] == "and"){
    tokenCount++;
    return AND;
  }

  else if(finalTokens[tokenCount] == "relop"){
    tokenCount++;
    statementCount++;
    return RELOP;
  }

  else if(finalTokens[tokenCount] == "addOp"){
    tokenCount++;
    return ADDOP;
  }

  else if(finalTokens[tokenCount] == "mulOp"){
    tokenCount++;
    return MULOP;
  }

  else if(finalTokens[tokenCount] == "number"){
    tokenCount++;
    return NUMBER;
  }

  cout << "The program is correct, and contains:" << endl;
  cout << statementCount << " statements" << endl;
  cout << functionCount << " function definitions" << endl;

  return -1;
}

