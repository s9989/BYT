package exercise4;

public abstract class Client {
	
	public static String formatString(String format, Person person) {
		
		format = format.replace("%first%", person.first);
		
		if (person.middle != null) {
			format = format.replace("%middle%", person.middle);
			format = format.replace("%space%", " ");
		} else {
			format = format.replace("%middle%", "");
			format = format.replace("%space%", "");
		}
		
		format = format.replace("%last%", person.last);
		
		return format;
		
	}
	
}
