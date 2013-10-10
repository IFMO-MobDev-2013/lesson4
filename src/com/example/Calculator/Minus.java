package com.example.Calculator;

public class Minus extends BinaryOperation {
    public Minus(Expression a, Expression b) {
        super(a, b);
    }

    protected Double calc(Double x, Double y) {
        return x - y;
    }

    protected Expression simplifyBin(Expression left, Expression right) {
        if (right instanceof Const && ((Const) right).getValue() == 0) {
            return left;
        }
        if (left instanceof Const && ((Const) left).getValue() == 0) {
            return new UnaryMinus(right);
        }
        if (left.equals(right)) {
            return new Const(0);
        }
        return new Minus(left, right);
    }

    public String toString() {
        String res = l.toString().concat(" - ");
        String right = r.toString();
        if (r instanceof Plus || r instanceof Minus) {
            right = Wrapper.wrap(right);
        }
        return res.concat(right);
    }
}
