package com.example.lesson4;

public enum BinaryOperationEnum {
	Plus {
		public double calculate(double a, double b)
				throws EvaluatingExcepction {
			return a + b;
		}

		public String symbol() {
			return "+";
		}

		public int getPriority() {
			return 1;
		}

		public boolean isRightAssociative() {
			return false;
		}

	},
	Minus {
		public double calculate(double a, double b) {
			return a - b;
		}

		public String symbol() {
			return "-";
		}

		public int getPriority() {
			return 1;
		}

		public boolean isRightAssociative() {
			return true;
		}

	},
	Times {
		public double calculate(double a, double b) {
			return a * b;
		}

		public String symbol() {
			return "*";
		}

		public int getPriority() {
			return 2;
		}

		public boolean isRightAssociative() {
			return false;
		}

	},
	Division {
		public double calculate(double a, double b)
				throws EvaluatingExcepction {
            if (b == 0)
            {
                throw new EvaluatingExcepction();
            }
			return a / b;
		}

		public String symbol() {
			return "/";
		}

		public int getPriority() {
			return 2;
		}

		public boolean isRightAssociative() {
			return true;
		}

	},
	Remainder {
		public double calculate(double a, double b) {
			return a % b;
		}

		public String symbol() {
			return "%";
		}

		public int getPriority() {
			return 2;
		}

		public boolean isRightAssociative() {
			return true;
		}

	},
	Degree {
		public double calculate(double a, double b)
				throws EvaluatingExcepction {
			/*
			 * if (a == 0 && b <= 0) { throw new
			 * EvaluatingExcepction("invalid operation " + a + "^" + b); }
			 */
			return Math.pow(a, b);
		}

		public String symbol() {
			return "^";
		}

		public int getPriority() {
			return 3;
		}

		public boolean isRightAssociative() {
			return false;
		}

	};
	public abstract double calculate(double a, double b) throws EvaluatingExcepction;

	public abstract String symbol();

	public abstract int getPriority();

	public abstract boolean isRightAssociative();
}
