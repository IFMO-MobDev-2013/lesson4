package com.example.Les4;

abstract public class BinaryOp implements Expression {

    public Expression left, right;

    public double evaluate() throws ExceptionDivisionByZero {
        return Calc(this.left.evaluate(), this.right.evaluate());
    }

    abstract protected double Calc(double a, double b) throws ExceptionDivisionByZero;

}
