package ru.marsermd.Swipylator.core;
import java.util.Map;

public interface Operand<T> {
	T evaluate(Map<String, T> values);
}
