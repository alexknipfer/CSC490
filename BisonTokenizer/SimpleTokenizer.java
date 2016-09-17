public class SimpleTokenizer{
	public static void main(String arg[]){
		SimpleLexer lex = new SimpleLexer(arg[0]);

		String t = lex.nextToken();

		while(t != null){
			System.out.println(t);
			t = lex.nextToken();
		}
	}
}
