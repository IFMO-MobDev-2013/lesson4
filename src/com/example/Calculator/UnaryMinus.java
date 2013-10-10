package com.example.Calculator;

import java.util.HashMap;

public class UnaryMinus extends Expression {
    Expression e;

    public UnaryMinus(Expression expression) {
        e = expression;
    }

    public Double evaluate(HashMap<String, Double> map) {
        return -e.evaluate(map);
    }

    Expression getE() {
        return e;
    }

    public String toString() {
        String res = e.toString();
        if (e instanceof Minus || e instanceof Plus || e instanceof Mod) {
            res = Wrapper.wrap(res);
        }
        return "-".concat(res);
    }

    public Expression simplify() {
        Expression tmp = e.simplify();
        if (e instanceof Const) {
            return new Const(-((Const) e).getValue());
        }
        return new UnaryMinus(tmp);
    }

    public boolean equals(Expression e) {
        return (e instanceof UnaryMinus && ((UnaryMinus) e).getE().equals(this.e));
    }
}
