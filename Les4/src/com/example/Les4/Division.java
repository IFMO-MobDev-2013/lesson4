package com.example.Les4;

public class Division extends BinaryOp {

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public double Calc(double a, double b) throws ExceptionDivisionByZero {
		if (b == 0.0) throw new ExceptionDivisionByZero();
        return a / b;
    }

}
