package ru.zulyaev.ifmo.lesson4.parser.evaluable;

import ru.zulyaev.ifmo.lesson4.parser.Evaluable;
import ru.zulyaev.ifmo.lesson4.parser.EvaluableSimplifier;
import ru.zulyaev.ifmo.lesson4.parser.VariableValues;
import ru.zulyaev.ifmo.lesson4.parser.exception.VariableValueNotSpecified;

final public class Variable<E> implements Evaluable<E> {
	private final String name;
	private final boolean caseSensitive;

	/**
	 * Construct evaluable variable
	 *
	 * @param name          variable name
	 * @param caseSensitive is variable name case sensitive
	 */
	public Variable(String name, boolean caseSensitive) {
		if (name == null) {
			throw new NullPointerException();
		}
		this.name = name;
		this.caseSensitive = caseSensitive;
	}

	/**
	 * Evaluate variable
	 *
	 * @param values variable values
	 * @return variable value gotten from {@code values}
	 */
	@Override
	public E evaluate(VariableValues<? extends E> values) throws VariableValueNotSpecified {
		E value = values.get(name, caseSensitive);
		if (value == null) {
			throw new VariableValueNotSpecified(name);
		}
		return value;
	}

	@Override
	public Evaluable<E> simplify(EvaluableSimplifier<E> simplifier) {
		return this;
	}

	/**
	 * returns variable name
	 */
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Variable variable = (Variable) o;

		if (caseSensitive != variable.caseSensitive) return false;

		if (caseSensitive) {
			return name.equals(variable.name);
		}

		return name.equalsIgnoreCase(variable.name);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + (caseSensitive ? 1 : 0);
		return result;
	}
}
