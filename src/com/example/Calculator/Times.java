package com.example.Calculator;

public class Times extends BinaryOperation {
    public Times(Expression a, Expression b) {
        super(a, b);
    }

    protected Double calc(Double x, Double y) {
        return x * y;
    }

    protected Expression simplifyBin(Expression left, Expression right) {
        if (right instanceof Const && ((Const) right).getValue() == 1) {
            return left;
        }
        if (left instanceof Const && ((Const) left).getValue() == 1) {
            return right;
        }
        if (right instanceof Const && ((Const) right).getValue() == 0) {
            return new Const(0);
        }
        if (left instanceof Const && ((Const) left).getValue() == 0) {
            return new Const(0);
        }
        return new Times(left, right);
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
        return left.concat(" * ".concat(right));
    }
}
