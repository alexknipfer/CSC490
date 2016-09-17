import java.util.*;
import java.io.*; 

public class SimpleLexer{
	
	private Scanner reader;

	public SimpleLexer(String fileName){
		try{
			reader = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e){
			System.err.println("File not found: " + fileName);
		}
	}

	public String nextToken(){
		String input;
		
		try{
			input = reader.next();
		}
		catch(Exception e){
			return null;
		}
		return input;
	}

}
