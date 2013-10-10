package ru.ifmo.ctddev.skripnikov.androidhw4;

class BinaryOperation implements Evaluable {
	private Evaluable x;
	private Evaluable y;
	private Operation o;

	BinaryOperation(Evaluable x, Evaluable y, Operation o) {
		this.x = x;
		this.y = y;
		this.o = o;
	}

	public double evaluate() throws EvaluateException {
		return o.apply(x.evaluate(), y.evaluate());
	}

	enum Operation {
		PLUS {
            public double apply(double l, double r)
					throws EvaluateException {
				return l + r;
			}
		},
		MINUS {
            public double apply(double l, double r)
					throws EvaluateException {
                return l - r;
			}
		},
		TIMES {
            public double apply(double l, double r)
					throws EvaluateException {
                return l * r;
			}
		},
		DIVISION {
            public double apply(double l, double r)
					throws EvaluateException {
                if(r == 0)
                    throw new EvaluateException(EvaluateException.Cause.DIVISION_BY_ZERO);
                return l / r;
			}
		};

		public abstract double apply(double l, double r)
				throws EvaluateException;
	}
}
