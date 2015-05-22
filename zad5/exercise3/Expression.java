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
		// and change names from 'l' and 'r'
		int leftValue = left.evaluate();
		int rightValue = right.evaluate();
		
		switch (op) {
		case '+':
			return leftValue + rightValue;
		case '-':
			return leftValue - rightValue;
		case '*':
			return leftValue * rightValue;
		case '/':
			return leftValue / rightValue;
		default:
			throw new IllegalStateException();
		}
	}
}
