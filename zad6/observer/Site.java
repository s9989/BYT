package observer;

import java.net.URLConnection;

import memento.Originator;

public class Site extends Originator {

	public Site(String url) {
		this.set(url, 0L);
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public Long getModifiedDate() {
		return this.modifiedDate;
	}
	
	public boolean hasChanged(URLConnection connection) {
		System.out.print("*"+connection.getLastModified()+"*");
		return connection.getLastModified() > this.modifiedDate;
	}
	
}
