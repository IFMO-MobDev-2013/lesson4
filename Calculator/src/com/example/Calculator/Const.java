package com.example.Calculator;

public class Const implements Evaluable {
    private double value;

    public Const(double a) {
	    this.value = a;
    }

    public double evaluate() {
	    return value;
    }
}