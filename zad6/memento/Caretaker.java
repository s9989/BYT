package memento;

import java.util.Date;
import java.util.Hashtable;

public class Caretaker {
	
	public Caretaker() {
		this.storage = new Hashtable<String, Memento>();
	}

	private Hashtable<String, Memento> storage;
	
	public void addMemento(Memento memento) {
		storage.put(memento.getUrl(), memento);
	}
	
	public Memento getMemento(String url) {
		if (storage.containsKey(url)) {
			return storage.get(url);
		} else {
			Date d = new Date();
			return new Memento(url, d.getTime()-1000L);
//			return new Memento(url, 0L);
		}
	}
	
}
