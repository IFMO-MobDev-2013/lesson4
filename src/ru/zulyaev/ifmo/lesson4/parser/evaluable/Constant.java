package ru.zulyaev.ifmo.lesson4.parser.evaluable;

import ru.zulyaev.ifmo.lesson4.parser.Evaluable;
import ru.zulyaev.ifmo.lesson4.parser.EvaluableSimplifier;
import ru.zulyaev.ifmo.lesson4.parser.VariableValues;

final public class Constant<E> implements Evaluable<E> {
    private final E value;

    /**
     * Construct constant representing specified value
     *
     * @param value constant' value
     */
    public Constant(E value) {
        this.value = value;
    }

    /**
     * Evaluate constant with specified variable values, <br/>
     * which are obviously ignored
     *
     * @param ignored variable values
     * @return constant' value
     */
    @Override
    public E evaluate(VariableValues<? extends E> ignored) {
        return value;
    }

    @Override
    public Evaluable<E> simplify(EvaluableSimplifier<E> simplifier) {
        return this;
    }

    /**
     * @return String.valueOf(value)
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constant constant = (Constant) o;

        return !(value != null ? !value.equals(constant.value) : constant.value != null);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
