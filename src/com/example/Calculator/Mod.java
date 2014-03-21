package com.example.Calculator;

public class Mod extends BinaryOperation {
    public Mod(Expression a, Expression b) {
        super(a, b);
    }

    protected Double calc(Double x, Double y) {
        return x % y;
    }

    protected Expression simplifyBin(Expression left, Expression right) {
        if (left.equals(right)) {
            return new Const(0);
        }
        return new Mod(left, right);
    }

    public String toString() {
        String left = l.toString();
        String right = r.toString();
        if (l instanceof Plus || l instanceof Minus) {
            left = Wrapper.wrap(left);
        }
        if (r instanceof Plus || r instanceof Minus) {
            right = Wrapper.wrap(right);
        }
        return left.concat(" % ".concat(right));
    }
}
