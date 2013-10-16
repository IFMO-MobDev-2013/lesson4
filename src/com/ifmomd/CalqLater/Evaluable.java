package com.ifmomd.CalqLater;

import java.util.Map;
import java.util.Set;

public interface Evaluable<T> {
	T evaluate(Map<String, T> vars);
	void collectVarNames(Set<String> vars);
	String toString();
}
