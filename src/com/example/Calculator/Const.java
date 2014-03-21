package com.example.Calculator;

import java.util.HashMap;

public class Const extends Expression {
    private double value;

    public Const(double x) {
        value = x;
    }

    public Double evaluate(HashMap<String, Double> map) {
        return value;
    }

    double getValue() {
        return value;
    }

    public String toString() {
        return (new Double(value)).toString();
    }

    public Expression simplify() {
        return this;
    }

    public boolean equals(Expression e) {
        return (e instanceof Const && ((Const) e).getValue() == value);
    }
}
