package com.example.homework4;

final public class Const implements Expression {
    private double value;

    public Const(double c) {
        value = c;
    }

    public double evaluate() {
        return value;
    }
}