package com.example.lesson4;

import java.util.Map;
import java.util.Vector;

public class Function extends Single {
	protected Vector<Evaluator> params = null;
	protected FunctionEnum type;
	private double result;

	public Function(FunctionEnum type, Vector<Evaluator> params) {
		this.type = type;
		this.params = params;
		this.prio = Integer.MAX_VALUE;
	}
	
	public double evaluate(Map<String, Double> values) throws EvaluatingExcepction {
		Vector<Double> cp = new Vector<Double>();
		for (int i = 0; i < params.size(); i++) {
			cp.add(params.get(i).evaluate(values));
		}
		result = type.calculate(cp);
		return result;
	}
}
