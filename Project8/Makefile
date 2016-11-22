Parser.class: Parser.java ICode.class SymbolTable.class Symbol.class
	javac Parser.java

Parser.java: project8.java
	byaccj -J project8.java

ICode.class: ICode.java Symbol.class
	javac ICode.java

SymbolTable.class: SymbolTable.java Symbol.class
	javac SymbolTable.java

Symbol.class: Symbol.java
	javac Symbol.java

clean:
	/bin/rm -f Parser.java ParserVal.java *.class *~
