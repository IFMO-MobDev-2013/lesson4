package ru.ifmo.ctddev.skripnikov.androidhw4;

class Const implements Evaluable {
	private final double value;

	Const(Double  value) {
		this.value = value;
	}

	public double evaluate() {
		return value;
	}
}
