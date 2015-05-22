package exercise4;

import java.io.*;

public class Client1 {

	public static void printPerson(Writer out, Person person) throws IOException {
		
		out.write(Client.formatString("%first% %middle%%space%%last%", person));
		
	}

}
