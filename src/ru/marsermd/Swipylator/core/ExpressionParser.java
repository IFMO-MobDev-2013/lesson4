package ru.marsermd.Swipylator.core;

public class ExpressionParser<T> {
	private String expression;
	private int position = 0; //current position in the string
	private BinaryOperator<T> plus;
	private BinaryOperator<T> minus;
	private BinaryOperator<T> times;
	private BinaryOperator<T> division;
	private BinaryOperator<T> modulus;
	private UnaryOperator<T> unaryMinus;
	private UnaryOperator<T> unaryPlus;
	private ConstFinder<T> finder;


	public ExpressionParser(BinaryOperator<T> plus, BinaryOperator<T> minus,
			BinaryOperator<T> times, BinaryOperator<T> division,
			BinaryOperator<T> modulus, UnaryOperator<T> unaryMinus,
			UnaryOperator<T> unaryPlus, ConstFinder<T> finder) {
		this.plus = plus;
		this.minus = minus;
		this.times = times;
		this.division = division;
		this.modulus = modulus;
		this.unaryMinus = unaryMinus;
		this.unaryPlus = unaryPlus;
		this.finder = finder;
	}

	public Operand<T> build(String expression) {
		this.expression = expression;
		skip("");
		Operand<T> expr = build(0);
		return expr;
	}

	private Operand<T> build(int state) {
		if (lastState(state)) {
			Operand<T> expr = null;
			boolean isMinus = startWith("-");
			if (isMinus) {
				skip("-");
			}

			boolean isPlus = startWith("+");
			if (isPlus) {
				skip("+");
			}

			if (startWith("(")) {
				skip("(");
				expr = build(0);
				skip(")");
			} else {
				expr = readSingle();
			}

			if (isMinus) {
				expr = new UnaryOperand<T>(unaryMinus, expr);
			}
			if (isPlus) {
				expr = new UnaryOperand<T>(unaryPlus, expr);
			}
			return expr;
		}

		Operand<T> left = build(state + 1);

		char op = 0;

		while((op = readOperatorByState(state)) != 0) {
			Operand<T> right = build(state + 1);
			left = buildOperand(left, right, op);
		}
		return left;
	}

	private static char [][] operands = new char[][] {
		{'+', '-'},
		{'*', '/', '%'},
		null
	};

	private boolean lastState(int s) {
		return s + 1 >= operands.length;
	}

	private boolean startWith(String s) {
		return expression.startsWith(s, position);
	}

	private void skip(String s) {
		if(startWith(s))
			position += s.length();
		while(position < expression.length() && 
				Character.isSpace(expression.charAt(position)))
			position++;
	}

	private char readOperatorByState(int state) {
		char[] ops = operands[state];
		for(char operand : ops) {
			if(startWith("" + operand)) {
				skip("" + operand);
				return operand;
			}
		}
		return 0;
	}

	private Operand<T> readSingle() {
		int initialPosition = position;

		while(position < expression.length()) {
			if(!(Character.isLetterOrDigit(expression.charAt(position)) ||
				expression.charAt(position) == ',' || expression.charAt(position) == '.'))
				break;
			position++;
		}

		if(position > initialPosition) {
			String s = expression.substring(initialPosition, position);
			skip(" ");
			if(Character.isDigit(s.charAt(0))){
				return new Const<T>(finder.find(s));
			}
			return new Variable<T>(s);
		}
		throw new IllegalArgumentException("constant or value expexted, but not found");
	}

	private Operand<T> buildOperand(Operand<T> left, Operand<T> right, char o) {
		switch (o) {
		case '+': return new BinaryOperand<T>(left, right, plus);
		case '-': return new BinaryOperand<T>(left, right, minus);
		case '*': return new BinaryOperand<T>(left, right, times);
		case '/': return new BinaryOperand<T>(left, right, division);
		case '%': return new BinaryOperand<T>(left, right, modulus);
		}
		throw new RuntimeException(); //@todo: fix
	}
}
