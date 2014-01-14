package com.example.Les4;

public class Times extends BinaryOp {

    public Times(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public double Calc(double a, double b) {
        return a * b;
    }

}



