package com.tourist.Calculator;

public class Const implements Expression {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    public double evaluate() {
        return this.value;
    }
}
