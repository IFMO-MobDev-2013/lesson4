package ru.zulyaev.ifmo.lesson4.parser.dbl;

import ru.zulyaev.ifmo.lesson4.parser.UnaryOperator;

public enum DoubleUnaryOperator implements UnaryOperator<Double> {
	MINUS("-") {
		@Override
		public Double calc(Double operand) {
			return -operand;
		}
	},
	PLUS("+") {
		@Override
		public Double calc(Double operand) {
			return operand;
		}
	};

	private final String token;

	private DoubleUnaryOperator(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return token;
	}
}
