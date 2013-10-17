package ru.zulyaev.ifmo.lesson4.parser.evaluable;

import ru.zulyaev.ifmo.lesson4.parser.Evaluable;
import ru.zulyaev.ifmo.lesson4.parser.EvaluableSimplifier;
import ru.zulyaev.ifmo.lesson4.parser.Function;
import ru.zulyaev.ifmo.lesson4.parser.VariableValues;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvaluableFunction<E> implements Evaluable<E> {
	private final Function<E> function;
	private final String token;
	private final List<Evaluable<E>> arguments;

	private EvaluableFunction(Function<E> function, List<? extends Evaluable<E>> arguments, String token) {
		if (function == null || arguments == null || arguments.contains(null)) {
			throw new NullPointerException();
		}
		this.function = function;
		this.arguments = Collections.unmodifiableList(new ArrayList<>(arguments));
		this.token = token;
	}

	/**
	 * Create new {@code EvaluableFunction} for {@code Function} with {@code List} of arguments. <br/>
	 * List of arguments is copied.
	 *
	 * @param function  function to evaluate
	 * @param arguments arguments to pass to function
	 * @param <E>       operand class
	 * @return created {@code EvaluableFunction}
	 * @throws IllegalArgumentException if function does not accept such argument count
	 */
	public static <E> EvaluableFunction<E> withArgumentsAndToken(Function<E> function, List<? extends Evaluable<E>> arguments, String token) {
		int cnt = arguments.size();
		if (!function.accepts(cnt)) {
			throw new IllegalArgumentException("Function '" + function + "' doesn't accept " + cnt + " arguments");
		}

		return new EvaluableFunction<>(function, arguments, token);
	}

	public static <E> EvaluableFunction<E> withArguments(Function<E> function, List<? extends Evaluable<E>> arguments) {
		return withArgumentsAndToken(function, arguments, null);
	}

	public Function<E> getFunction() {
		return function;
	}

	public List<Evaluable<E>> getArguments() {
		return arguments;
	}

	@Override
	public E evaluate(VariableValues<? extends E> values) throws EvaluationException {
		List<E> argumentValues = new ArrayList<>(arguments.size());
		for (Evaluable<E> expression : arguments) {
			argumentValues.add(expression.evaluate(values));
		}
		return function.calc(argumentValues);
	}

	@Override
	public Evaluable<E> simplify(EvaluableSimplifier<E> simplifier) throws EvaluationException {
		return simplifier.simplifyFunction(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(token == null ? function.toString() : token);
		builder.append("(");

		for (int i = 0; i < arguments.size(); ++i) {
			Evaluable<E> evaluable = arguments.get(i);

			if (i > 0) {
				builder.append(", ");
			}

			builder.append(evaluable.toString());
		}

		builder.append(")");

		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof EvaluableFunction)) return false;

		EvaluableFunction that = (EvaluableFunction) o;

		return arguments.equals(that.arguments) &&
				function.equals(that.function);
	}

	@Override
	public int hashCode() {
		int result = function.hashCode();
		result = 31 * result + arguments.hashCode();
		return result;
	}
}
