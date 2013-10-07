package ru.zulyaev.ifmo.lesson4.parser.dbl;

import ru.zulyaev.ifmo.lesson4.parser.BinaryOperator;

public enum DoubleBinaryOperator implements BinaryOperator<Double> {
	PLUS("+", true) {
		@Override
		public Double calc(Double first, Double second) {
			return first + second;
		}
	},
	MINUS("-", false) {
		@Override
		public Double calc(Double first, Double second) {
			return first - second;
		}
	},
	TIMES("*", true) {
		@Override
		public Double calc(Double first, Double second) {
			return first * second;
		}
	},
	DIVISION("/", false) {
		@Override
		public Double calc(Double first, Double second) {
			return first / second;
		}
	},
	POWER("^", false) {
		@Override
		public Double calc(Double first, Double second) {
			return Math.pow(first, second);
		}
	};

	private final String token;
	private final boolean associative;

	private DoubleBinaryOperator(String token, boolean associative) {
		this.token = token;
		this.associative = associative;
	}

	@Override
	public boolean isAssociative() {
		return associative;
	}

	@Override
	public String toString() {
		return token;
	}
}
