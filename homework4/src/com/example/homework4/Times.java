package com.example.homework4;

public final class Times extends BinaryOperation {
    public Times(Expression L, Expression R) {
        super(L, R);
    }

    protected double calc(double a, double b) {
        return a * b;
    }
}