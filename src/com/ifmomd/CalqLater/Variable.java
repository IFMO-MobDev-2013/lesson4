package com.ifmomd.CalqLater;

import java.util.Map;
import java.util.Set;

public class Variable<T> implements Evaluable<T> {
	private String name;

	Variable(String name) {
		this.name = name;
	}

	@Override
	public T evaluate(Map<String, T> vars) {
		return vars.get(name);
	}
	
	@Override
	public void collectVarNames(Set<String> vars) {
		if (!vars.contains(name))
			vars.add(name);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
