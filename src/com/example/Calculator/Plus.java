package com.example.Calculator;

public class Plus extends BinaryOperation {
    public Plus(Expression a, Expression b) {
        super(a, b);
    }

    protected Double calc(Double x, Double y) {
        return x + y;
    }

    protected Expression simplifyBin(Expression left, Expression right) {
        if (right instanceof Const && ((Const) right).getValue() == 0) {
            return left;
        }
        if (left instanceof Const && ((Const) left).getValue() == 0) {
            return right;
        }
        return new Plus(left, right);
    }

    public String toString() {
        return l.toString().concat(" + ".concat(r.toString()));
    }
}
