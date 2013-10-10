package com.example.Calculator;

public class Plus extends BinaryOperation {
    public Plus(Evaluable first, Evaluable second) {
	    super(first, second);
    }

    public double apply(double first, double second) {
	    return (first + second);
    }
}