package com.ifmomd.CalqLater;

public abstract class NumberFactory<T> {
	abstract T parseNumber(String s);
	abstract T getNull();
	abstract String getPattern();
}
