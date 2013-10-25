package com.example.homework4;

final public class Minus extends BinaryOperation {
    public Minus(Expression L, Expression R) {
        super(L, R);
    }

    protected double calc(double a, double b) {
        return a - b;
    }
}