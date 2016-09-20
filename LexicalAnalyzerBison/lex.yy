%{
  #include <cstring>
  #include <fstream>
  #include <iostream>
  #include <string>

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
