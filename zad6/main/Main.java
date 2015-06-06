package main;
import java.util.Timer;

import observer.Client;
import observer.Fetcher;
import timer.MyTimerTask;


public class Main {

	public static void main(String[] args) {

//		MyTimerTask task = new MyTimerTask();
//
//		Timer timer = new Timer();
//		timer.schedule(task, 0l, 3000l);
		
		Fetcher f = new Fetcher();
		
		f.addClient(new Client("http://www.4lc.pl"));
		f.addClient(new Client("http://www.wp.pl"));
		f.addClient(new Client("http://www.o2.pl"));
		
		f.fetch();
	}

}
