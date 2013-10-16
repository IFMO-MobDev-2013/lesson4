package ru.marsermd.Swipylator.core;

public enum IntegerBinaryOperator implements BinaryOperator<Integer> {
	PLUS {
		@Override
		public Integer calculate(Integer left, Integer right) {
			return left + right;
		}
	},
	MINUS {
		@Override
		public Integer calculate(Integer left, Integer right) {
			return left - right;
		}
	},
	TIMES {
		@Override
		public Integer calculate(Integer left, Integer right) {
			return left * right;
		}
	},
	DIVISION {
		@Override
		public Integer calculate(Integer left, Integer right) {
			return left / right;
		}
	},
	MODULE {
		@Override
		public Integer calculate(Integer left, Integer right) {
			return left % right;
		}
	}
}
