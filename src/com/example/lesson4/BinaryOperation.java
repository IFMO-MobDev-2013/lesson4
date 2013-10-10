package com.example.lesson4;

import java.util.Map;

public class BinaryOperation extends Evaluator {
	protected Evaluator left = null;
	protected Evaluator right = null;
	protected BinaryOperationEnum type;

	public BinaryOperation(BinaryOperationEnum type, Evaluator left,
			Evaluator right) {
		this.type = type;
		this.left = left;
		this.right = right;
        this.prio = type.getPriority();
	}

	public double evaluate(Map<String, Double> values) throws EvaluatingExcepction {
		return type.calculate(left.evaluate(values), (right.evaluate(values)));
	}

	public String toString() {
		String l = left + "";
		String r = right + "";
		if (left.prio < this.prio) {
			l = "(" + left + ")";
		}
		if (right.prio < this.prio
				|| (right.prio == this.prio && type.isRightAssociative())) {
			r = "(" + right + ")";
		}
		return l + " " + type.symbol() + " " + r;
	}
}
