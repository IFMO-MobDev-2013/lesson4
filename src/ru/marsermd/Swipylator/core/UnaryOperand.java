package ru.marsermd.Swipylator.core;

import java.util.Map;

public class UnaryOperand<T> implements Operand<T> {
	private UnaryOperator<T> operator;
	private Operand<T> body;
	
	public UnaryOperand(UnaryOperator<T> operator, Operand<T> body) {
		super();
		this.operator = operator;
		this.body = body;
	}

	@Override
	public T evaluate(Map<String, T> values) {
		return operator.calculate(body.evaluate(values));
	}
}
