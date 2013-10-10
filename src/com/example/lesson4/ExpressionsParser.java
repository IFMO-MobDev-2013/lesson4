package com.example.lesson4;

import java.util.Vector;

public class ExpressionsParser {
	static class Lex {
		public static final int INVALID = -1;
		public static final int NUMBER = 0;
		public static final int FUNCTION = 1;
		public static final int PLUS = 2;
		public static final int MINUS = 3;
		public static final int TIMES = 4;
		public static final int DIVISION = 5;
		public static final int DEGREE = 6;
		public static final int DELIMETER = 7;
		public static final int REMAINDER = 8;
		public String value = "";
		public int type = 0;

		public Lex(String value, int type) {
			this.value = value;
			this.type = type;
		}
	}

	public Evaluator parse(String s) throws Exception {
		return lE(s);
	}

	private Evaluator lE(String s) throws Exception {
		Vector<Lex> arr = splitE(s);
		Evaluator r = new Const(0);
		if (arr.size() == 0) {
			return r;
		}
		r = lT(arr.get(0).value);
		for (int i = 1; i < arr.size(); i++) {
			Evaluator t = lT(arr.get(i).value);
			if (arr.get(i).type == Lex.PLUS) {
				r = new BinaryOperation(BinaryOperationEnum.Plus, r, t);
			} else {
				r = new BinaryOperation(BinaryOperationEnum.Minus, r, t);
			}
		}

		return r;
	}

	private Evaluator lT(String s) throws Exception {
		Vector<Lex> arr = splitT(s);
		Evaluator r = new Const(1);
		if (arr.size() == 0) {
			return r;
		}
		r = lD(arr.get(0).value);
		for (int i = 1; i < arr.size(); i++) {
			Evaluator t = lD(arr.get(i).value);
			if (arr.get(i).type == Lex.TIMES) {
				r = new BinaryOperation(BinaryOperationEnum.Times, r, t);
			} else if (arr.get(i).type == Lex.DIVISION) {
				r = new BinaryOperation(BinaryOperationEnum.Division, r, t);
			} else {
				r = new BinaryOperation(BinaryOperationEnum.Remainder, r, t);
			}
		}
		return r;
	}

	private Evaluator lF(String s) throws Exception {
		if (s.length() == 0) {
			throw new Exception("Parse error");
		}
		Evaluator r;
		if (s.charAt(0) == '(' && s.charAt(s.length() - 1) == ')') {
			r = lE(s.substring(1, s.length() - 1));
		} else if (isNumber(s)) {
			r = new Const(Double.parseDouble(s));
		} else if (isVariable(s)) {
			r = new Variable(s);
		} else {
			r = parseFunction(s);
		}
		return r;
	}

	private Evaluator lD(String s) throws Exception {
		Vector<Lex> arr = splitD(s);
		Evaluator r = new Const(1);
		if (arr.size() == 0) {
			return r;
		}
		r = lF(arr.get(0).value);
		for (int i = 1; i < arr.size(); i++) {
			Evaluator t = lF(arr.get(i).value);
			r = new BinaryOperation(BinaryOperationEnum.Degree, r, t);
		}
		return r;
	}

	private Evaluator parseFunction(String s) throws Exception {
		Evaluator r;
		int index = s.indexOf("(");
		String name = s.substring(0, index);
		Vector<Lex> params = splitFuncParams(s.substring(index + 1,
				s.length() - 1));
		Vector<Evaluator> t = new Vector<Evaluator>();
		for (int i = 0; i < params.size(); i++) {
			t.add(lE(params.get(i).value));
		}
        if (name.equals("sqrt")) {
            r = new Function(FunctionEnum.Sqrt, t);

        } else if (name.equals("log")) {
            r = new Function(FunctionEnum.Log, t);

        } else if (name.equals("ln")) {
            r = new Function(FunctionEnum.Ln, t);

        } else if (name.equals("sin")) {
            r = new Function(FunctionEnum.Sin, t);

        } else if (name.equals("cos")) {
            r = new Function(FunctionEnum.Cos, t);

        } else if (name.equals("tg")) {
            r = new Function(FunctionEnum.Tg, t);

        } else {
            throw new ParsingException("Unknown function \"" + name + "\"");
        }
		return r;
	}

	private int getTypeByChar(char c) {
		switch (c) {
		case '+':
			return Lex.PLUS;
		case '-':
			return Lex.MINUS;
		case '*':
			return Lex.TIMES;
		case '/':
			return Lex.DIVISION;
		case '^':
			return Lex.DEGREE;
		case ',':
			return Lex.DELIMETER;
		case '%':
			return Lex.REMAINDER;
		default:
			return Lex.INVALID;
		}
	}

	private Vector<Lex> splitE(String s) {
		StringBuilder temp = new StringBuilder();
		Vector<Lex> result = new Vector<Lex>();
		int sup = Lex.PLUS;
		int d = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (d == 0 && (c == '+' || c == '-')) {
				if (temp.length() > 0) {
					result.add(new Lex(temp.toString(), sup));
					temp.setLength(0);
				} else {
					result.add(new Lex("0", sup));
				}
				sup = getTypeByChar(c);
			} else {
				if (c == '(') {
					d++;
				} else if (c == ')') {
					d--;
				}
				if (c != ' ') {
					temp.append(c);
				}
			}
		}
		if (temp.length() > 0) {
			result.add(new Lex(temp.toString(), sup));
		}
		return result;
	}

	private Vector<Lex> splitD(String s) {
		StringBuilder temp = new StringBuilder();
		Vector<Lex> result = new Vector<Lex>();
		int sup = Lex.DEGREE;
		int d = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (d == 0 && (c == '^')) {
				result.add(new Lex(temp.toString(), sup));
				temp.setLength(0);
				sup = getTypeByChar(c);
			} else {
				if (c == '(') {
					d++;
				} else if (c == ')') {
					d--;
				}
				if (c != ' ') {
					temp.append(c);
				}
			}
		}
		if (temp.length() > 0) {
			result.add(new Lex(temp.toString(), sup));
		}
		return result;
	}

	private Vector<Lex> splitFuncParams(String s) {
		StringBuilder temp = new StringBuilder();
		Vector<Lex> result = new Vector<Lex>();
		int sup = Lex.DEGREE;
		int d = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (d == 0 && (c == ',')) {
				result.add(new Lex(temp.toString(), sup));
				temp.setLength(0);
				sup = getTypeByChar(c);
			} else {
				if (c == '(') {
					d++;
				} else if (c == ')') {
					d--;
				}
				if (c != ' ') {
					temp.append(c);
				}
			}
		}
		if (temp.length() > 0) {
			result.add(new Lex(temp.toString(), sup));
		}
		return result;
	}

	private boolean isVariable(String s) {
		char[] illegalChars = { '(', ')', '{', '$', '@', '^', ',', '.' };
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < illegalChars.length; j++) {
				if (illegalChars[j] == s.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++) {
			int k = (int) s.charAt(i) - 48;
			if ((k < 0 || k > 9) && s.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}

	private Vector<Lex> splitT(String s) {
		StringBuilder temp = new StringBuilder();
		Vector<Lex> result = new Vector<Lex>();
		int sup = Lex.TIMES;
		int d = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (d == 0 && (c == '*' || c == '/' || c == '%')) {
				result.add(new Lex(temp.toString(), sup));
				temp.setLength(0);
				sup = getTypeByChar(c);
			} else {
				if (c == '(') {
					d++;
				} else if (c == ')') {
					d--;
				}
				if (c != ' ') {
					temp.append(c);
				}
			}
		}
		if (temp.length() > 0) {
			result.add(new Lex(temp.toString(), sup));
		}
		return result;
	}
}
