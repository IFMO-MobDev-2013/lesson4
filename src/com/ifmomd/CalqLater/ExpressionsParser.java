package com.ifmomd.CalqLater;


public class ExpressionsParser<T> {
	
	public static final String REGEX_PATTERN_WORD = "^[A-Za-z]+$";
	
	static <T> Evaluable<T> parseExpression(String s, Operator<T> addOperator, Operator<T> subtractOperator, Operator<T> multiplyOperator, Operator<T> divideOperator, Operator<T> moduloOperator, NumberFactory<T> factory) {
		if (!s.isEmpty()) {
			while (s.length() > 1 && isExpressionInBrackets(s))
				s = s.substring(1, s.length()-1);
			OperationMatch op = getFirstOperatorPosition(s);
			if (op.pos == -1)
				return parseLiteralOrVariable(s, factory);
			else {
				Evaluable<T> left = parseExpression(s.substring(0, op.pos), addOperator, subtractOperator, multiplyOperator, divideOperator, moduloOperator, factory),
					     right = parseExpression(s.substring(op.pos + 1, s.length()), addOperator, subtractOperator, multiplyOperator, divideOperator, moduloOperator, factory);
				return "+".equals(op.operationIdentifier) ? new BinaryOperation<T>(left, right, addOperator)
					 : "-".equals(op.operationIdentifier) ? new BinaryOperation<T>(left, right, subtractOperator)
					 : "*".equals(op.operationIdentifier) ? new BinaryOperation<T>(left, right, multiplyOperator)
					 : "/".equals(op.operationIdentifier) ? new BinaryOperation<T>(left, right, divideOperator)
					 : "%".equals(op.operationIdentifier) ? new BinaryOperation<T>(left, right, moduloOperator) : null;
			}
		} else {
			return new Const<T>(factory.getNull());
		}
	}
	
	static <T>Evaluable<T> parseLiteralOrVariable(String s, NumberFactory<T> factory) {
		if (s.matches(factory.getPattern()) && s.length() > 0) {
			return new Const<T>(factory.parseNumber(s));
		} else
			return new Variable<T>(s);
	}

	private static boolean isExpressionInBrackets(String s) {
		int depth = 0;
		for (int i = 0; i<s.length() - 1; i++) {
			if (s.charAt(i) == '(') depth++;
			if (s.charAt(i) == ')') depth--;
			if (depth == 0) 
				return false;
		}
		return true;
	}

	static final String[] operationsByPriority = new String[] { "+", "-", "*", "/", "%", "^" };

	private static OperationMatch getFirstOperatorPosition(String s) {
		for (String operation : operationsByPriority) {
			int brackets = 0;
            boolean right = operation.equals("+") || operation.equals("-");
			for (int i = (right ? s.length()-1 : 0); right ? i>=0 : i < s.length() - operation.length(); i+=right?-1:1) {
				if (s.charAt(i) == '(')
					brackets++;
				if (s.charAt(i) == ')')
					brackets--;
				if (brackets == 0
						&& s.substring(i, i + operation.length()).equals(
								operation)
                        && !(operation.equals("-") && i > 0 && s.toLowerCase().charAt(i - 1) == 'e')) {
					return new OperationMatch(i, operation);
				}
			}
		}
		return new OperationMatch(-1, "");
	}

	private static class OperationMatch {
		int pos;
		String operationIdentifier;

		public OperationMatch(int pos, String operationIdentifier) {
			this.pos = pos;
			this.operationIdentifier = operationIdentifier;
		}
	}
	
}
