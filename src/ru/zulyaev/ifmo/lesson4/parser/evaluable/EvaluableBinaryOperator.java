package ru.zulyaev.ifmo.lesson4.parser.evaluable;

import ru.zulyaev.ifmo.lesson4.parser.BinaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.Evaluable;
import ru.zulyaev.ifmo.lesson4.parser.EvaluableSimplifier;
import ru.zulyaev.ifmo.lesson4.parser.VariableValues;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

public class EvaluableBinaryOperator<E> implements EvaluableOperator<E> {
	private final BinaryOperator<E> operator;
	private final int priority;
	private final boolean rightAssociative;
	private final String token;
	private final Evaluable<E> left, right;

	public EvaluableBinaryOperator(
			BinaryOperator<E> operator,
			int priority,
			boolean rightAssociative,
			Evaluable<E> left,
			Evaluable<E> right,
			String token) {
		if (operator == null || left == null || right == null) {
			throw new NullPointerException();
		}
		this.operator = operator;
		this.priority = priority;
		this.rightAssociative = rightAssociative;
		this.left = left;
		this.right = right;
		this.token = token;
	}

	public EvaluableBinaryOperator(
			BinaryOperator<E> operator,
			int priority,
			boolean rightAssociative,
			Evaluable<E> left,
			Evaluable<E> right) {
		this(operator, priority, rightAssociative, left, right, null);
	}

	@Override
	public int getPriority() {
		return priority;
	}

	public BinaryOperator<E> getOperator() {
		return operator;
	}

	public Evaluable<E> getLeft() {
		return left;
	}

	public Evaluable<E> getRight() {
		return right;
	}

	@Override
	public E evaluate(VariableValues<? extends E> values) throws EvaluationException {
		return operator.calc(left.evaluate(values), right.evaluate(values));
	}

	@Override
	public Evaluable<E> simplify(EvaluableSimplifier<E> simplifier) throws EvaluationException {
		return simplifier.simplifyBinary(this);
	}

	private String wrapOperand(Evaluable<E> operand, boolean left) {
		String result = operand.toString();
		if (operand instanceof EvaluableOperator) {
			int innerPriority = ((EvaluableOperator<E>) operand).getPriority();

			if (innerPriority < priority ||
					innerPriority == priority &&
							!operator.isAssociative() &&
							left == rightAssociative) {
				result = "(" + result + ")";
			}
		}
		return result;
	}

	@Override
	public String toString() {
		String op = token == null ? operator.toString() : token;

		return wrapOperand(left, true) + " " + op + " " + wrapOperand(right, false);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof EvaluableBinaryOperator)) return false;

		EvaluableBinaryOperator that = (EvaluableBinaryOperator) o;

		return operator.equals(that.operator) &&
				left.equals(that.left) &&
				right.equals(that.right);
	}

	@Override
	public int hashCode() {
		int result = operator.hashCode();
		result = 31 * result + left.hashCode();
		result = 31 * result + right.hashCode();
		return result;
	}
}
