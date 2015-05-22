/**
 * Kalkulator (co najmniej 4 operacje), 
 * z użyciem wzorca "łańcuch zobowiązań" 
 * (chain of command design pattern)
 *  
 * @author Jakub Kuryłowicz
 */

package wzorce_projektowe;

abstract class Processor {
	
	public static int MUL = 4;
	public static int DIV = 3;
	public static int ADD = 2;
	public static int SUB = 1;
	
	private Processor next = null;
	private int priority;
	
	public Processor(int priority) {
		this.priority = priority;
	}
	
	public void setNext(Processor processor) {
		this.next = processor;
	}
	
	public int process(int x, int y, int action) throws Exception {
		if (this.priority <= action) {
			try {
				return this.execute(x, y);
			} catch (Exception e) {
				throw e;
			}
		}
		if (this.next != null) 
			return this.next.process(x, y, action);
		
		return 0;
	}
	
	abstract public int execute(int x, int y) throws Exception;
}

class Multiplication extends Processor {
	
	public Multiplication(int priority) {
		super(priority);
	}
	
	@Override
	public int execute(int x, int y) {
		return x * y;
	}
	
}

class Division extends Processor {
	
	public Division(int priority) {
		super(priority);
	}
	
	@Override
	public int execute(int x, int y) throws Exception {
		if (y == 0) throw new Exception("Cannot deivide by 0");
		return x / y;
	}
	
}

class Addition extends Processor {
	
	public Addition(int priority) {
		super(priority);
	}
	
	@Override
	public int execute(int x, int y) {
		return x + y;
	}
	
}

class Substraction extends Processor {
	
	public Substraction(int priority) {
		super(priority);
	}
	
	@Override
	public int execute(int x, int y) throws Exception {
		return x - y;
	}
	
}


public class ChainOfCommand {
	
	private static Processor createCalculator() {
		
		Processor mul = new Multiplication(Processor.MUL);
		Processor div = new Division(Processor.DIV);
		Processor add = new Addition(Processor.ADD);
		Processor sub = new Substraction(Processor.SUB);
		
		mul.setNext(div);
		div.setNext(add);
		add.setNext(sub);
		
		return mul;
	}

	public static void main(String[] args) {
		
		Processor engine = ChainOfCommand.createCalculator();
		
		try {
			
			System.out.print("5 * 3 = ");
			System.out.println(engine.process(5, 3, Processor.MUL));
			
			System.out.print("1 + 7 = ");
			System.out.println(engine.process(1, 7, Processor.ADD));
			
			System.out.print("6 / 2 = ");
			System.out.println(engine.process(6, 2, Processor.DIV));
			
			System.out.print("9 - 4 = ");
			System.out.println(engine.process(9, 4, Processor.SUB));
			
			System.out.print("1 / 0 = ");
			System.out.println(engine.process(1, 0, Processor.DIV));
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}

	}

}
