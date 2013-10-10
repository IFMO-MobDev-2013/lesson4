package com.example.Calculator;

public abstract class BinaryOperation implements Evaluable {
    private Evaluable left;
    private Evaluable right;

    public BinaryOperation(Evaluable left, Evaluable right) {
	    this.left = left;
	    this.right = right;
    }

    protected abstract double apply(double first, double second);

    public double evaluate() {
	    return apply(left.evaluate(), right.evaluate());
    }
}