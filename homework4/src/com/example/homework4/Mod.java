package com.example.homework4;

public final class Mod extends BinaryOperation {
    public Mod(Expression L, Expression R) {
        super(L, R);
    }

    protected double calc(double a, double b) {
        return a % b;
    }
}