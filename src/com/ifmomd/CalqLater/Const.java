package com.ifmomd.CalqLater;

import java.util.Map;
import java.util.Set;

public class Const<T> implements Evaluable<T> {
	T value;
	
	public static final double EPSILON = 0.00000000001;

	Const(T value) {
		this.value = value;
	}

	@Override
	public
	T evaluate(Map<String, T> vars) {
		return value;
	}
	
	@Override
	public void collectVarNames(Set<String> vars) {
		return;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
