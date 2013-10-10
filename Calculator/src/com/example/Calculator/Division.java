package com.example.Calculator;

public class Division extends BinaryOperation {
    public Division(Evaluable first, Evaluable second) {
	    super(first, second);
    }

    public double apply(double first, double second) {
	    return (first / second);
    }
}