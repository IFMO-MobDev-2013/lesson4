package com.example.Les4;

public class ParserExpression {

	public static Expression Parser(String s) throws ExceptionWrongExpression {
		long k = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (k == 0) {

				if (i != 0 && s.charAt(i) == '+') {
					return new Plus(Parser(s.substring(0, i)), Parser(s.substring(i + 1)));
				} else if (i != 0 && s.charAt(i) == '-') {
					return new Minus(Parser(s.substring(0, i)), Parser(s.substring(i + 1)));
				}

				if (s.charAt(i) == '*') {
					return new Times(Parser(s.substring(0, i)), Parser(s.substring(i + 1)));
				} else if (s.charAt(i) == '/') {
					return new Division(Parser(s.substring(0, i)), Parser(s.substring(i + 1)));
				}

				if (i == 0) {
					if (s.charAt(i) == '+') {
						return new Plus(Parser("0"), Parser(s.substring(i + 1)));
					} else if (s.charAt(i) == '-') {
						return new Minus(Parser("0"), Parser(s.substring(i + 1)));
					} else
						return new Const(Double.parseDouble(s));
				}
			}
			if (s.charAt(i) == ')') {
				if (i+1 != s.length() && s.charAt(i+1) == '(')  throw new ExceptionWrongExpression();
				k--;
			} else if (s.charAt(i) == '(') {
				if (i-1 != -1 && s.charAt(i+1) == ')' )  throw new ExceptionWrongExpression();
				k++;
			}
		}

		if (s.length() <= 1) throw new ExceptionWrongExpression();
		return Parser(s.substring(1, s.length() - 1));


	}
}