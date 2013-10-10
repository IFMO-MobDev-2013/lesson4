package com.example.Calculator;

public class Times extends BinaryOperation {
    public Times(Evaluable first, Evaluable second) {
	    super(first, second);
    }

    public double apply(double first, double second) {
	    return (first * second);
    }
}