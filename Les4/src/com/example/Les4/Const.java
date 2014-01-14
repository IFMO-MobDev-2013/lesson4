package com.example.Les4;

public class Const implements Expression {

    protected double value;

    public Const(double value) {
        this.value = value;
    }

    public double evaluate() {
        return this.value;
    }

}
