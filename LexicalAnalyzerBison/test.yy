%{
  #include <cstring>
  #include <fstream>
  #include <iostream>
  #include <string>
  #include <vector>

  using namespace std;

  int yylex();
  int yyerror(const char *message){cerr << "Error!!" << endl; return 0;};

%}

%union
{
	double dval;
	char *sval;
}

%token<sval> FUNCTION
%token<sval> ID
%token<sval> PARENL
%token<sval> PARENR
%token<sval> CURLL
%token<sval> CURLR
%token<sval> VAR
%token<sval> SEMICOLON
%token<sval> COMMA
%token<sval> ASSIGNOP
%token<sval> STRING
%token<sval> IF
%token<sval> ELSE
%token<sval> WHILE
%token<sval> OR
%token<sval> NOT
%token<sval> AND
%token<sval> RELOP
%token<sval> ADDOP
%token<sval> MULOP
%token<sval> NUMBER

%%
  pgm: pgmpart pgm | pgmpart;

  pgmpart: vardecl | function;

  vardecl: VAR varlist SEMICOLON;

  varlist: ID COMMA varlist;

  function: FUNCTION ID PARENL PARENR body
    | FUNCTION ID PARENL fplist PARENR body;

  body: CURLL bodylist CURLR;

  fplist: ID COMMA fplist | ID;

  bodylist: vardecl bodylist | stmt bodylist | /*epsilon*/;

  stmt: assign SEMICOLON
    | fcall SEMICOLON
    | while
    | if
    | body;

  assign: ID ASSIGNOP;

  expr: factor | expr ADDOP factor;

  factor: term | factor MULOP term;

  term: ID
    | NUMBER
    | PARENL expr PARENR
    | ADDOP term
    | fcall;

  bexpr: bfactor | bexpr OR bfactor;

  bfactor: bneg | bfactor AND bneg;

  bneg: bterm | NOT bterm;

  bterm: expr RELOP expr | PARENL bterm PARENR;

  fcall: ID PARENL PARENR
    | ID PARENL aplist PARENR;

  aplist: expr COMMA aplist
    | expr
    | STRING;

  while: WHILE PARENL bexpr PARENR stmt;

  if: IF PARENL bexpr PARENR stmt
    | IF PARENL bexpr PARENR stmt ELSE stmt;
%%

void myCopy(char* &into, const string &from)
{
	into = new char[from.length() +1];
	strcpy(into, from.c_str());
}

ifstream inputFile;

int main(int argc, char *argv[])
{
	inputFile.open(argv[1], ifstream::in);

	if(!inputFile)
	{
		cerr << "Could not open input file:" << argv[1] << endl;
		return 1;
	}

	yyparse();

	return 0;
}

int yylex()
{
  return -1;
}
