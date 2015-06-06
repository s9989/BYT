package memento;

public class Memento {
	
	private String url;
	private long modifiedDate;
	
	public Memento(String url, long modifiedDate) {
		this.url = url;
		this.modifiedDate = modifiedDate;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public long getModifiedDate() {
		return this.modifiedDate;
	}
	
}
