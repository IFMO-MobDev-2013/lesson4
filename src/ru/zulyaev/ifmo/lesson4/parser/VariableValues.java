package ru.zulyaev.ifmo.lesson4.parser;

public interface VariableValues<E> {
    /**
     * Set specified value for variable with specified name
     *
     * @param name  variable name
     * @param value variable value
     * @return {@code this}
     */
    VariableValues<E> set(String name, E value);

    /**
     * Get variable value for specified name and case sensitivity
     *
     * @param name          variable name
     * @param caseSensitive is name case sensitive
     * @return variable value or {@code null} if no value assigned
     */
    E get(String name, boolean caseSensitive);
}
