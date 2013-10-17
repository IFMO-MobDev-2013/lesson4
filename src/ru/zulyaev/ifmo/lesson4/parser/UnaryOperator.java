package ru.zulyaev.ifmo.lesson4.parser;

import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

/**
 * @param <E> operand class
 */
public interface UnaryOperator<E> extends Operator<E> {
	/**
	 * Perform operator calculations on specified operand
	 *
	 * @param operand operand
	 * @return result of calculations
	 */
	E calc(E operand) throws EvaluationException;
}
