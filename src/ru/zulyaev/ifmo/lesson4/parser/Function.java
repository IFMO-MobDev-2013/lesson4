package ru.zulyaev.ifmo.lesson4.parser;

import java.util.List;

/**
 * @param <E> operand class
 */
public interface Function<E> {
    /**
     * Perform calculations of function with specified arguments list
     *
     * @param arguments list of arguments
     * @return function value with specified arguments
     */
    E calc(List<? extends E> arguments);

    /**
     * Test if function accepts argument list with specified count of arguments
     *
     * @param argumentCount count of arguments
     * @return true if function accepts such arguments count, false otherwise
     */
    boolean accepts(int argumentCount);
}
