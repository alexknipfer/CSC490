Parser.class: Parser.java ParserVal.class SimpleLexer.class
	javac Parser.java

ParserVal.class: ParserVal.java
	javac ParserVal.java

SimpleLexer.class: SimpleLexer.java
	javac SimpleLexer.java

Parser.java: SimpleCalc.by
	byaccj -J SimpleCalc.by

clean:
	/bin/rm -f *.class Parser.java ParserVal.java *.o

