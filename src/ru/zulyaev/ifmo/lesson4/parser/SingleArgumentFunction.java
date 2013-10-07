package ru.zulyaev.ifmo.lesson4.parser;

import java.util.List;

/**
 * Basic class for functions that accept exactly one argument. <br/>
 * (i.e. sin, cos, tan, sqrt, etc.)
 *
 * @param <E>
 */
public abstract class SingleArgumentFunction<E> implements Function<E> {
    /**
     * {@inheritDoc}
     */
    @Override
    public final E calc(List<? extends E> arguments) {
        return calc(arguments.get(0));
    }

    /**
     * {@inheritDoc}
     *
     * @param argumentCount count of arguments
     * @return {@code argumentCount == 1}
     */
    @Override
    public final boolean accepts(int argumentCount) {
        return argumentCount == 1;
    }

    /**
     * Perform calculations with exactly one argument
     *
     * @param value argument
     * @return function value with specified argument
     */
    public abstract E calc(E value);
}
