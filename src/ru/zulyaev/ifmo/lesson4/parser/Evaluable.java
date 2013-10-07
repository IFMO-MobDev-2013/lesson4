package ru.zulyaev.ifmo.lesson4.parser;

import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

/**
 * @param <E> class of expression operands
 */
public interface Evaluable<E> {
	/**
	 * Evaluate expression with specified variable values
	 *
	 * @param values variable values
	 * @return evaluation result
	 * @throws NullPointerException if no value assign to variable
	 */
	E evaluate(VariableValues<? extends E> values) throws EvaluationException;

	/**
	 * Get simplified version of {@code this} evaluable
	 * <br/>
	 * Implementation realises "Visitor" pattern
	 *
	 * @param simplifier simplifier visitor
	 * @return {@code this} simplified by {@code simplifier}
	 */
	Evaluable<E> simplify(EvaluableSimplifier<E> simplifier) throws EvaluationException;
}
