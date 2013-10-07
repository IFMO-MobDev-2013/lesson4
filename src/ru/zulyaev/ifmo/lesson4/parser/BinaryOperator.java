package ru.zulyaev.ifmo.lesson4.parser;

import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

public interface BinaryOperator<E> extends Operator<E> {
	/**
	 * Perform operator calculations on specified operands
	 *
	 * @param first  first operand
	 * @param second second operand
	 * @return result of calculations
	 */
	E calc(E first, E second) throws EvaluationException;

	/**
	 * Get operators' associativity
	 *
	 * @return {@code true} if {@code calc(calc(a, b), c) == calc(a, calc(b, c))}
	 *         for all {@code a}, {@code b} and {@code c}
	 */
	boolean isAssociative();
}
