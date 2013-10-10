package com.example.lesson4;

import java.util.Map;

public class Const extends Single {
	private double value;

	public Const(double value) {
		this.value = value;
		this.prio = Integer.MAX_VALUE;
	}

	public double evaluate(Map<String, Double> values) {
		return this.value;
	}

	public String toString() {
		return value + "";
	}
}
