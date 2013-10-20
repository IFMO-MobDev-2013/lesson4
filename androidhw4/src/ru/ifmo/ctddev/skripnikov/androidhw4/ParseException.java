package ru.ifmo.ctddev.skripnikov.androidhw4;

public class ParseException extends Exception {
	private final String expression;
	private final int index;

	public ParseException(String expression, int index) {
		this.expression = expression;
		this.index = index;
	}

	public String getMessage() {
		return "Unexpected character in "
				+ expression.substring(0, expression.length() - 1) + " at "
				+ index + " position!";
	}
}
