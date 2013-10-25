package com.example.homework4;

public abstract class BinaryOperation implements Expression {
    protected Expression LHS;
    protected Expression RHS;

    public BinaryOperation(Expression L, Expression R) {
        this.LHS = L;
        this.RHS = R;
    }

    abstract protected double calc(double a, double b);

    public double evaluate() {
        return calc(LHS.evaluate(), RHS.evaluate());
    }
}