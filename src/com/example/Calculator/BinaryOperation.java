package com.example.Calculator;

import java.util.HashMap;

public abstract class BinaryOperation extends Expression {
    protected Expression l, r;

    public BinaryOperation(Expression left, Expression right) {
        l = left;
        r = right;
    }

    protected abstract Double calc(Double x, Double y);

    protected abstract Expression simplifyBin(Expression left, Expression right);

    public Double evaluate(HashMap<String, Double> map) {
        return calc(l.evaluate(map), r.evaluate(map));
    }

    Expression left() {
        return l;
    }

    Expression right() {
        return r;
    }

    public Expression simplify() {
        Expression lsimple = l.simplify();
        Expression rsimple = r.simplify();
        if (lsimple instanceof Const && rsimple instanceof Const) {
            HashMap<String, Double> tmp = new HashMap<String, Double>();
            return new Const(calc(lsimple.evaluate(tmp), rsimple.evaluate(tmp)));
        }
        return simplifyBin(lsimple, rsimple);
    }

    public boolean equals(Expression e) {
        return (this.getClass().equals(e.getClass()) && l.equals(((BinaryOperation) e).left()) && r.equals(((BinaryOperation) e).right()));
    }
}