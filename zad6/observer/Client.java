package observer;

public class Client {

	public String url;
	
	public Client(String url) {
		this.url = url;
	}
	
	public void alert() {
		System.out.println("[" + this.url + "] has changed!");
	}
	
}
