package ru.marsermd.Swipylator.core;
import java.util.Map;

public class BinaryOperand<T> implements Operand<T> {
	
	private Operand<T> left, right;
	private BinaryOperator<T> operator;
	
	protected BinaryOperand(Operand<T> left, Operand<T> right, BinaryOperator<T> operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	@Override
	public T evaluate(Map<String, T> values) {
		return operator.calculate(left.evaluate(values), right.evaluate(values));
	}
}
