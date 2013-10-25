package com.example.homework4;

final public class Division extends BinaryOperation {
    public Division(Expression L, Expression R) {
        super(L, R);
    }

    protected double calc(double a, double b) {
        return a / b;
    }
}
