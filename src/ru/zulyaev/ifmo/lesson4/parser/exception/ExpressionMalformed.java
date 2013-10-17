package ru.zulyaev.ifmo.lesson4.parser.exception;

public class ExpressionMalformed extends Exception {
	public ExpressionMalformed(String message) {
		super(message);
	}

	public ExpressionMalformed(String message, Throwable cause) {
		super(message, cause);
	}
}
