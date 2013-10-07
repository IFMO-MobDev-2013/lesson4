package ru.zulyaev.ifmo.lesson4.parser;

import java.util.HashMap;
import java.util.Map;

final public class BasicVariableValues<E> implements VariableValues<E> {
    private final Map<String, E> caseSensitiveValues = new HashMap<>();
    private final Map<String, E> caseInsensitiveValues = new HashMap<>();

    public BasicVariableValues() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if {@code value == null}
     */
    public BasicVariableValues<E> set(String name, E value) {
        if (value == null) {
            throw new NullPointerException("Value is null");
        }

        caseSensitiveValues.put(name, value);
        caseInsensitiveValues.put(name.toLowerCase(), value);

        return this;
    }

    /**
     * {@inheritDoc}
     * <br/>
     * If different values were assigned to one name more than once,
     * <br/>
     * last one will be returned.
     */
    @Override
    public E get(String name, boolean caseSensitive) {
        if (caseSensitive) {
            return caseSensitiveValues.get(name);
        } else {
            return caseInsensitiveValues.get(name.toLowerCase());
        }
    }
}