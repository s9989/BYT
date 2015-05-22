package exercise4;



public class Client4 {

	public static String toString(Person person) {
		
		return Client.formatString("%last%, %first%%space%%middle%", person);
		
	}

}
