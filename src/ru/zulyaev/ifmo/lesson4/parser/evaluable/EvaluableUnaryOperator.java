package ru.zulyaev.ifmo.lesson4.parser.evaluable;

import ru.zulyaev.ifmo.lesson4.parser.Evaluable;
import ru.zulyaev.ifmo.lesson4.parser.EvaluableSimplifier;
import ru.zulyaev.ifmo.lesson4.parser.UnaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.VariableValues;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

public class EvaluableUnaryOperator<E> implements EvaluableOperator<E> {
	private final UnaryOperator<E> operator;
	private final int priority;
	private final boolean rightAssociative;
	private final String token;
	private final Evaluable<E> operand;

	public EvaluableUnaryOperator(
			UnaryOperator<E> operator,
			int priority,
			boolean rightAssociative,
			Evaluable<E> operand,
			String token) {
		if (operator == null || operand == null) {
			throw new NullPointerException();
		}
		this.operator = operator;
		this.priority = priority;
		this.rightAssociative = rightAssociative;
		this.operand = operand;
		this.token = token;
	}

	public EvaluableUnaryOperator(
			UnaryOperator<E> operator,
			int priority,
			boolean rightAssociative,
			Evaluable<E> operand) {
		this(operator, priority, rightAssociative, operand, null);
	}

	@Override
	public int getPriority() {
		return priority;
	}

	public Evaluable<E> getOperand() {
		return operand;
	}

	public UnaryOperator<E> getOperator() {
		return operator;
	}

	public boolean isRightAssociative() {
		return rightAssociative;
	}

	@Override
	public E evaluate(VariableValues<? extends E> values) throws EvaluationException {
		return operator.calc(operand.evaluate(values));
	}

	@Override
	public Evaluable<E> simplify(EvaluableSimplifier<E> simplifier) throws EvaluationException {
		return simplifier.simplifyUnary(this);
	}

	@Override
	public String toString() {
		String result = operand.toString();
		if (operand instanceof EvaluableOperator) {
			int innerPriority = ((EvaluableOperator) operand).getPriority();

			if (innerPriority < priority ||
					innerPriority == priority &&
							!rightAssociative) {
				result = "(" + result + ")";
			}
		}

		return (token == null ? operator.toString() : token) + result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof EvaluableUnaryOperator)) return false;

		EvaluableUnaryOperator that = (EvaluableUnaryOperator) o;

		return operator.equals(that.operator) && operand.equals(that.operand);
	}

	@Override
	public int hashCode() {
		int result = operator.hashCode();
		result = 31 * result + operand.hashCode();
		return result;
	}
}
