package com.example.lesson4;

import java.util.Map;

public class Variable extends Single {
	private String name = "";

	public Variable(String name) {
		this.name = name;
		this.prio = Integer.MAX_VALUE;
	}

	public String getName() {
		return name;
	}

	public double evaluate(Map<String, Double> values) throws EvaluatingExcepction {
		if (!values.containsKey(name)) {
			throw new EvaluatingExcepction("Unknown variable \"" + name + "\"");
		}
		return values.get(name);
	}

	public String toString() {
		return name + "";
	}

	public Evaluator simplify() {
		return this;
	}
}
