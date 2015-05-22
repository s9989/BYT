package exercise3;

public class Expression {

	private char op;

	private Expression left;

	private Expression right;

	private int constant;

	public Expression(int constant) {
		this.op = 'c';
		this.constant = constant;
	}

	public Expression(char op, Expression left, Expression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

	public int evaluate() {
		
		// quick check if expression is a constant
		if (op == 'c') return constant;
		
		// calculate before entering switch
		int l = left.evaluate();
		int r = right.evaluate();
		
		switch (op) {
		case '+':
			return l + r;
		case '-':
			return l - r;
		case '*':
			return l * r;
		case '/':
			return l / r;
		default:
			throw new IllegalStateException();
		}
	}
}
