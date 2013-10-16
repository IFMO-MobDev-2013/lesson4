package ru.marsermd.Swipylator.core;

public enum DoubleUnaryOperator implements UnaryOperator<Double>{
	PLUS {
		@Override
		public Double calculate(Double body) {
			return body;
		}
	},
	MINUS {
		@Override
		public Double calculate(Double body) {
			return - body;
		}
	}
}
