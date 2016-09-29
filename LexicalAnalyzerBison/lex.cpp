%{
  #include <cstring>
  #include <fstream>
  #include <iostream>
  #include <string>
  #include <vector>

  using namespace std;

  int yylex();
  int yyerror(const char *message){cerr << "This program is incorrect, error!" << endl; return 0;};

    //initialize statement count and function count
  int statementCount = 0;
  int functionCount = 0;


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

  varlist: ID COMMA varlist | ID;

  function: FUNCTION ID PARENL PARENR body {functionCount++;}
    | FUNCTION ID PARENL fplist PARENR body {functionCount++;};

  body: CURLL bodylist CURLR;

  fplist: ID COMMA fplist | ID;

  bodylist: vardecl bodylist | stmt bodylist | ;

  stmt: assign SEMICOLON {statementCount++;}
    | fcall SEMICOLON {statementCount++;}
    | while {statementCount++;}
    | if {statementCount++;}
    | body;

  assign: ID ASSIGNOP expr;

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
vector<string> finalTokens;
int tokenCount;

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
  //functionCount = 0;

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
    //see if token is a variable, return the token
  if(finalTokens[tokenCount] == "var"){
    tokenCount++;
    return VAR;
  }

    //see if token is an ID, return the token
  else if(finalTokens[tokenCount] == "id"){
    tokenCount++;
    return ID;
  }

    //see if token is a semicolon, return the token
  else if(finalTokens[tokenCount] == "semicolon"){
    tokenCount++;
    return SEMICOLON;
  }

    //see if token is a function keyword, return the token
  else if(finalTokens[tokenCount] == "function"){
    tokenCount++;
    //functionCount++;
    return FUNCTION;
  }

    //see if token is a left parenthesis, return the token
  else if(finalTokens[tokenCount] == "parenL"){
    tokenCount++;
    return PARENL;
  }

    //see if the token is a right parenthesis, return the token
  else if(finalTokens[tokenCount] == "parenR"){
    tokenCount++;
    return PARENR;
  }

    //see if the token is a left curly brace, return the token
  else if(finalTokens[tokenCount] == "curlL"){
    tokenCount++;
    return CURLL;
  }

    //see if the token is a right curly brace, return the token
  else if(finalTokens[tokenCount] == "curlR"){
    tokenCount++;
    return CURLR;
  }

    //see if the token is a comma, return the token
  else if(finalTokens[tokenCount] == "comma"){
    tokenCount++;
    return COMMA;
  }

    //see if the token is a assignment operator, return the token
  else if(finalTokens[tokenCount] == "assignOp"){
    tokenCount++;
    return ASSIGNOP;
  }

    //see if the token is a string, return the token
  else if(finalTokens[tokenCount] == "string"){
    tokenCount++;
    return STRING;
  }

    //see if the token is a if, return the token
  else if(finalTokens[tokenCount] == "if"){
    tokenCount++;
    return IF;
  }

    //see if the token is a else, return the token
  else if(finalTokens[tokenCount] == "else"){
    tokenCount++;
    return ELSE;
  }

    //see if the token is a while, return the token
  else if(finalTokens[tokenCount] == "while"){
    tokenCount++;
    return WHILE;
  }


    //see if the token is a or, return the token
  else if(finalTokens[tokenCount] == "or"){
    tokenCount++;
    return OR;
  }

    //see if the token is a not, return the token
  else if(finalTokens[tokenCount] == "not"){
    tokenCount++;
    return NOT;
  }

    //see if the token is a and, return the token
  else if(finalTokens[tokenCount] == "and"){
    tokenCount++;
    return AND;
  }

    //see if the token is a relop, return the token
  else if(finalTokens[tokenCount] == "relop"){
    tokenCount++;
    return RELOP;
  }

    //see if the token is a add operator, return the token
  else if(finalTokens[tokenCount] == "addOp"){
    tokenCount++;
    return ADDOP;
  }

    //see if the token is a multiplication operator, return the token
  else if(finalTokens[tokenCount] == "mulOp"){
    tokenCount++;
    return MULOP;
  }

    //see if the token is a number, return the token
  else if(finalTokens[tokenCount] == "number"){
    tokenCount++;
    return NUMBER;
  }

  cout << "The program is correct, and contains:" << endl;
  cout << statementCount << " statements" << endl;
  cout << functionCount << " function definitions" << endl;

  return -1;
}
