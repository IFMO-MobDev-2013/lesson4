package com.example.lesson4;

import java.util.Map;

abstract public class Evaluator {
	abstract public double evaluate(Map<String, Double> values) throws EvaluatingExcepction;

	public int prio = 0;
}
