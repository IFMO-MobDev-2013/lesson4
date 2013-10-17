package ru.marsermd.Swipylator.core;
import java.util.Map;

public class Const<T> implements Operand<T> {
	private T value;

	public Const(T value) {
		this.value = value;
	}

	@Override
	public T evaluate(Map<String, T> ignored) {
		return value;
	}
}
