package exercise4;

import java.io.*;

public class Client3 {
	
	public static void display(Writer out, Person person) throws IOException {
		
		out.write(Client.formatString("%last%, %first%%space%%middle%", person));
		
	}

}
