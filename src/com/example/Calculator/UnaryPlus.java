package com.example.Calculator;

import java.util.HashMap;

public class UnaryPlus extends Expression {
    Expression e;

    public UnaryPlus(Expression expression) {
        e = expression;
    }

    public Double evaluate(HashMap<String, Double> map) {
        return e.evaluate(map);
    }

    public String toString() {
        return e.toString();
    }

    Expression getE() {
        return e;
    }

    public Expression simplify() {
        return e.simplify();
    }

    public boolean equals(Expression e) {
        return (e instanceof UnaryPlus && ((UnaryPlus) e).getE().equals(this.e));
    }
}
