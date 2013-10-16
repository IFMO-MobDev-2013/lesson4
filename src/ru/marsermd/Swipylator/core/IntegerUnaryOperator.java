package ru.marsermd.Swipylator.core;

public enum IntegerUnaryOperator implements UnaryOperator<Integer>{
	PLUS {
		@Override
		public Integer calculate(Integer body) {
			return body;
		}
	},
	MINUS {
		@Override
		public Integer calculate(Integer body) {
			return - body;
		}
	}
}
