package ru.ifmo.ctddev.skripnikov.androidhw4;

public class EvaluateException extends Exception {
	private final Cause cause;

	public EvaluateException(Cause cause) {
		this.cause = cause;
	}

	public String getMessage() {
		return cause.toString();
	}

	public enum Cause {
		DIVISION_BY_ZERO {
			public String toString() {
				return "Division by zero";
			}
		},
		OVERFLOW {
			public String toString() {
				return "Overflow";
			}
		};
		public abstract String toString();
	}
}
