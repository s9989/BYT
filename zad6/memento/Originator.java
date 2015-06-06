package memento;

public abstract class Originator {

	protected String url;
	protected Long modifiedDate;
	
	public void set(String url, Long modifiedDate) { 
		this.url = url;
		this.modifiedDate = modifiedDate;
	}
	
	public Memento saveToMemento() { 
		return new Memento(this.url, this.modifiedDate);
	}
	
	public void restoreFromMemento(Memento memento) {
		this.url = memento.getUrl();
		this.modifiedDate = memento.getModifiedDate();
	}
	
}
