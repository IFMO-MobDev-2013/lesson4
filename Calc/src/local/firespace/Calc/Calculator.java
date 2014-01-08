package local.firespace.Calc;

import java.util.LinkedList;

public class Calculator {
	private Calculator() {}

	private static final char UNARY_PLUS = 'p';
	private static final char UNARY_MINUS = 'm';
	private static final double EPS = 0.00000000001d;

	private static int getOperatorPriority(char c) {
		switch (c) {
			case ('*'):
			case ('/'): return 2;
			case ('+'):
			case ('-'): return 1;
			default:    return 0;
		}
	}

	private static void compute(char operator, LinkedList<Double> numbers) throws CalculateException{
		if (numbers.size() < 2 && !(operator == UNARY_MINUS || operator == UNARY_PLUS)) { throw new CalculateException("Illegal expression!"); }

		switch (operator) {
			case (UNARY_MINUS): {
				numbers.add((-1)*numbers.removeLast());
				break;
			}
			case (UNARY_PLUS): {
				numbers.add(numbers.removeLast());
				break;
			}
			case ('+'): {
				double right = numbers.removeLast();
				double left = numbers.removeLast();
				numbers.add(left + right);
				break;
			}
			case ('-'): {
				double right = numbers.removeLast();
				double left = numbers.removeLast();
				numbers.add(left - right);
				break;
			}
			case ('*'): {
				double right = numbers.removeLast();
				double left = numbers.removeLast();
				numbers.add(left * right);
				break;
			}
			case ('/'): {
				double right = numbers.removeLast();
				double left = numbers.removeLast();
				if (right < EPS) { throw new CalculateException("Division by zero!"); }
				numbers.add(left / right);
				break;
			}
		}
	}

	public static String calculate(String expression) throws CalculateException {
		LinkedList<Double> numbers = new LinkedList<Double>();
		LinkedList<Character> operators = new LinkedList<Character>();

		char currentChar;
		for (int i = 0; i < expression.length(); ) {
			currentChar = expression.charAt(i);
			switch (currentChar) {
				case ('('): {
					operators.add(currentChar);
					i++;
					break;
				}
				case (')'): {
					while (operators.getLast() != '(') { compute(operators.removeLast(), numbers); }
					operators.removeLast();
					i++;
					break;
				}
				case ('+'):
				case ('-'):
				case ('*'):
				case ('/'): {
					while (!operators.isEmpty() && getOperatorPriority(operators.getLast()) >= getOperatorPriority(currentChar)) { compute(operators.removeLast(), numbers); }

					if (i == 0 || expression.charAt(i-1) == '(') { operators.add((currentChar == '+') ? UNARY_PLUS : UNARY_MINUS); }
					else { operators.add(currentChar); }
					i++;
					break;
				}
				default: {
					String number = "";
					while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
						number += expression.charAt(i);
						i++;
					}
					if (number.charAt(number.length()-1) == '.') { numbers.add(Double.parseDouble(number.substring(0, number.length()-1))); }
					else { numbers.add(Double.parseDouble(number)); }
				}
			}
		}

		while (!operators.isEmpty()) { compute(operators.removeLast(), numbers); }
		if (numbers.size() == 0) return "0";

		return numbers.get(0).toString();
	}
}
