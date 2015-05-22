package exercise4;



public class Client2 {

	public static String formatPerson(Person person) {
		
		return Client.formatString("%last%, %first%%space%%middle%", person);
		
	}

}
