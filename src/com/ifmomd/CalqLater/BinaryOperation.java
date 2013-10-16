package com.ifmomd.CalqLater;

import java.util.Map;
import java.util.Set;

public class BinaryOperation<T> implements Evaluable<T> {

	Operator<T> operator;
	Evaluable<T> operand1, operand2;

	BinaryOperation(Evaluable<T> operand1, Evaluable<T> operand2, Operator<T> operator) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operator = operator;
	}

	@Override
	public T evaluate(Map<String, T> vars) {
		return operator.apply(operand1.evaluate(vars), operand2.evaluate(vars));
	}

	@Override
	public void collectVarNames(Set<String> vars) {
		operand1.collectVarNames(vars);
		operand2.collectVarNames(vars);
	}
}