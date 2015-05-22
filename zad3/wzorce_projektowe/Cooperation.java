/**
 * Cooperation - przykład użycia 
 * wzorca pula obiektów (object pool).
 * Odliczanie do wybranej liczby.
 *  
 * @author Jakub Kuryłowicz
 */
package wzorce_projektowe;

import java.util.Vector;

interface ManagerBase {
	public void countTo(int n);
	public void debug();
	public void tryToAssignJob(int start, int stop);
	public void iAmDone();
}

class Counter implements Runnable {
	
	public Thread thread;
	public String name;
	private Manager manager;
	public int delay;
	
	public Counter(String name, Manager manager) {
		this.name = name;
		this.manager = manager;
		this.delay = (int) (Math.random() * 400);
	}
	
	public int start;
	public int stop;
	public int c;
	
	public void start(int start, int stop) {
		this.start = start;
		this.stop = stop;
		this.c = start;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	@Override
	public void run() {
		try {
			for (int i= this.start; i < this.stop; i ++) {
				this.c = i;
				this.manager.debug();
				if (this.manager.c < this.c) this.manager.c = this.c;
				Thread.sleep(200 + this.delay);
			}
		} catch (InterruptedException e) {
			// interrupted
		}
		// task over
//		System.out.format("[*] %s status: %s\n", this.name, this.thread.getState());
		
		this.manager.alert(this);
	}
	
	float getProgress() {
		float a = (float) (this.c - this.start) / (float) (this.stop - this.start);
		return Math.round(a*10000)/100;
	}
}

class Manager {
	
	private Vector<Counter> free = new Vector<Counter>();
	private Vector<Counter> busy = new Vector<Counter>();
	
	private int lastNumber = 0;
	public int c = 0;
	
	public Manager() {
		this.free.add(new Counter("counter-1", this));
		this.free.add(new Counter("counter-2", this));
		this.free.add(new Counter("counter-3", this));
		this.free.add(new Counter("counter-4", this));
		this.free.add(new Counter("counter-5", this));
	}
	
	public void countTo(int number, int portion) {
		
		try {
			while (this.c < number - portion) {
//				System.out.format("%d <> %d\n", this.c, number - portion);
				this.tryToAssignJob(this.lastNumber, this.lastNumber + portion);
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			// sth wrong
		}
		
	}
	
	public void debug() {
		
		Vector<Counter> temp = new Vector<Counter>();
		temp = this.busy;
		
		for (Counter c : temp) {
			System.out.format("%4d# %s: %3d -> %3d -> %3d [%3.0f%%]\n", 
			c.delay+200, c.name, c.start, c.c, c.stop, c.getProgress());
		}
		
		temp = this.free;
		
		for (Counter c : temp) {
			System.out.format("%4d# %s --free-- \n", c.delay, c.name);
		}
		System.out.println("");
		
	}
	
	public void alert(Counter c) {
		this.busy.remove(c);
		this.free.add(c);
	}
	
	public void tryToAssignJob(int start, int stop) {
		
//		System.out.format("[+] Trying to assign job: (%d, %d)\n", start, stop);
		
		if (!this.free.isEmpty()) {
			Counter c = this.free.firstElement();
			this.free.remove(c);
			this.busy.add(c);
			c.start(start, stop);
			this.lastNumber = stop;
			
		}
	}
	
	
}

public class Cooperation {

	public static void main(String[] args) {
		
		Manager m = new Manager();
		m.countTo(100, 5);
		
	}

}
