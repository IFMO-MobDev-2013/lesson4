package ru.marsermd.Swipylator.core;
import java.util.Map;

public class Variable<T> implements Operand<T> {
	private String name;

	public Variable(String name) {
		this.name = name.toLowerCase();
	}

	@Override
	public T evaluate(Map<String, T> values) {
		return values.get(name);
	}
}
