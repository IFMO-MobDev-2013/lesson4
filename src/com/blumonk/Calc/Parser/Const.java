package com.blumonk.Calc.Parser;

public class Const implements Evaluable {

	private final double value;
	
	public Const(double value) {
		this.value = value;
	}
	
	public double evaluate() {
		return value;
	}	
	
}
