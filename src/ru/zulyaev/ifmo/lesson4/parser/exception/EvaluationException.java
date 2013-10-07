package ru.zulyaev.ifmo.lesson4.parser.exception;

public class EvaluationException extends Exception {
	public EvaluationException() {
	}

	public EvaluationException(String message) {
		super(message);
	}

	public EvaluationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EvaluationException(Throwable cause) {
		super(cause);
	}
}
