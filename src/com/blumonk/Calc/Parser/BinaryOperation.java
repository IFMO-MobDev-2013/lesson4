package com.blumonk.Calc.Parser;

public enum BinaryOperation {
	PLUS {
		@Override
		public double apply(double x, double y) {
			return x + y;
		}
	},
	
	MINUS {
		@Override
		public double apply(double x, double y) {
			return x - y;
		}
	},
	
	DIVISION {
		@Override
		public double apply(double x, double y) throws CalcException {
			if (y == 0) {
				throw new CalcException("Division by zero");
			}
			return x / y;			
		}
	},
	
	TIMES {
		@Override
		public double apply(double x, double y) {
			return x * y;
		}	
	},
	
	MOD {
		@Override
		public double apply(double x, double y) throws CalcException {
			if (y == 0) {
				throw new CalcException("Division by zero");
			}
			return x % y;
		}
	},
	
	POWER {
		@Override
		public double apply(double x, double y) throws CalcException {
			if (y != 0 && (Math.pow(Double.MAX_VALUE, 1/y) < x || Math.pow(Double.MIN_VALUE, 1/y) > x)) {
				throw new CalcException("Overflow");
			}
			return Math.pow(x, y);
		}
	};
	
	public abstract double apply(double x, double y) throws CalcException;
}
