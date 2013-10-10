package com.example.lesson4;

import java.util.Vector;

public enum FunctionEnum {
	Sqrt {
		public double calculate(Vector<Double> params) {
			return Math.sqrt(params.get(0));
		}

	},
	Log {
		public double calculate(Vector<Double> params) throws EvaluatingExcepction {
            if (params.size() < 2)
            {
                throw new EvaluatingExcepction();
            }
			return Math.log(params.get(1)) / Math.log(params.get(0));
		}

	},
	Ln {
		public double calculate(Vector<Double> params) {
			return Math.log(params.get(0));
		}

	},
	Sin {
		public double calculate(Vector<Double> params) {
			return Math.sin(params.get(0));
		}

	},
	Cos {
		public double calculate(Vector<Double> params) {
			return Math.cos(params.get(0));
		}

	},
	Tg {
		public double calculate(Vector<Double> params) {
			return Math.tan(params.get(0));
		}

	};
	public abstract double calculate(Vector<Double> params) throws EvaluatingExcepction;
}
