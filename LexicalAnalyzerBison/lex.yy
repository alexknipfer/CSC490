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

  vardecl: VAR varlist SEMICOLON {cout << "its a variable" << endl;};

  varlist: ID COMMA varlist | ID;

  function: FUNCTION ID PARENL PARENR body
    | FUNCTION ID PARENL fplist PARENR body;

  body: CURLL bodylist CURLR;

  fplist: ID COMMA fplist | ID;

  bodylist: vardecl bodylist | stmt bodylist | ;

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
vector<char> token;

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
    return "function";
  }

  else if(isAssignmentOperator(currentToken)){
    return "ASSIGNOP";
  }

    //checks if token is a if keyword
  else if(isIf(currentToken)){
    return "if";
  }

    //checks if token is a else keyword
  else if(isElse(currentToken)){
    return "else";
  }

    //checks if token is a not symbol
  else if(isNot(currentToken)){
    return "not";
  }

    //checks if token is a or keyword
  else if(isOr(currentToken)){
    return "or";
  }

    //checks if token is a VAR keyword
  else if(isVar(currentToken)){
    return "var";
  }

    //checks if token is a WHILE keyword
  else if(isWhile(currentToken)){
    return "while";
  }

  //checks if token is a semicolon
  else if(isSemicolon(currentToken)){
    return "semicolon";
  }

    //checks if token is a left parentesis
  else if(isParenL(currentToken)){
    return "parenL";
  }

  else if(isParenR(currentToken)){
    return "parenR";
  }

    //checks if token is a comma
  else if(isComma(currentToken)){
    return "comma";
  }

    //checks if token is left curly
  else if(isCurlL(currentToken)){
    return "curlL";
  }

    //checks if token is right curly
  else if(isCurlR(currentToken)){
    return "curlR";
  }

    //checks if token is a ADDOP (+ or -)
  else if(isAddOp(currentToken)){
    return "addOp";
  }

    //checks if token is a MULLOP (/ or *)
  else if(isMulOp(currentToken)){
    return "mulOp";
  }

    //checks if token is an AND
  else if(isAnd(currentToken)){
    return "and";
  }

    //checks if token is a RELOP
  else if(isRelopString(currentToken)){
    return "relop";
  }

    //checks if token is a ID
  else if(isID(currentToken)){
    return "id";
  }

  return "";
}

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
  string currentToken;
  string myToken;
  string readLine;
  char lookahead;
  string myNumber = "";
  string myString = "";
  int tempX;

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
          myToken = analyzeToken(token);
          cout << myToken << endl;
          if(myToken == "var"){
            myCopy(yylval.sval, myToken.c_str());
            return VAR;
          }
          token.clear();
        }

          //analyze token since symbol is read
          //once a symbol is reached, pop the symbol off the vector because the
          //previously a token was reached
        else if(isSymbol(lookahead)){
          token.pop_back();
          myToken = analyzeToken(token);
          cout << myToken << endl;
          if(myToken == "id"){
            myCopy(yylval.sval, myToken.c_str());
            return ID;
          }
          token.clear();
          token.push_back(lookahead);
          myToken = analyzeToken(token);
          cout << myToken << endl;
          if(myToken == "semicolon"){
            myCopy(yylval.sval, myToken.c_str());
            return SEMICOLON;
          }
          token.clear();
        }


        }
      }
      return -1;
    }
