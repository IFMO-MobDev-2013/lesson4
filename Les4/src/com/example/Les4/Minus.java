package com.example.Les4;

public class Minus extends BinaryOp {

    public Minus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public double Calc(double a, double b) {
        return a - b;
    }

}
