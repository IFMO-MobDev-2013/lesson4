package ru.marsermd.Swipylator.core;

public enum DoubleBinaryOperator implements BinaryOperator<Double> {
	PLUS {
		@Override
		public Double calculate(Double left, Double right) {
			return left + right;
		}
	},
	MINUS {
		@Override
		public Double calculate(Double left, Double right) {
			return left - right;
		}
	},
	TIMES {
		@Override
		public Double calculate(Double left, Double right) {
			return left * right;
		}
	},
	DIVISION {
		@Override
		public Double calculate(Double left, Double right) {
			return left / right;
		}
	},
	MODULE {
		@Override
		public Double calculate(Double left, Double right) {
			System.out.println(left + " " + right);
			return left % right;
		}
	}
}
