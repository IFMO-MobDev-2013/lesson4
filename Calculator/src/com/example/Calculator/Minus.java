package com.example.Calculator;

public class Minus extends BinaryOperation {
    public Minus(Evaluable first, Evaluable second) {
	    super(first, second);
    }
    
    public double apply(double first, double second) {
	    return (first - second);
    }
}	