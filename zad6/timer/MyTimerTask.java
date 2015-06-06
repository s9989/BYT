package timer;
import java.util.TimerTask;

import observer.Fetcher;


public class MyTimerTask extends TimerTask {

	Fetcher fetcher;
	
	public MyTimerTask() {
		super();
		this.fetcher = new Fetcher();
	}
	
	@Override
	public void run() {
		this.fetcher.fetch();
	}
	
}
