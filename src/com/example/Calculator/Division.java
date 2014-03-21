package com.example.Calculator;

public class Division extends BinaryOperation {
    public String toString() {
        String res = l.toString();
        if (l instanceof Plus || l instanceof Minus) {
            res = Wrapper.wrap(res);
        }
        String rightRes = r.toString();
        if (r instanceof BinaryOperation) {
            rightRes = Wrapper.wrap(rightRes);
        }
        return res.concat(" / ".concat(rightRes));
    }

    public Division(Expression a, Expression b) {
        super(a, b);
    }

    protected Double calc(Double x, Double y) {
        return x / y;
    }

    protected Expression simplifyBin(Expression left, Expression right) {
        if (left instanceof Const && ((Const) left).getValue() == 0) {
            return new Const(0);
        }
        if (right instanceof Const && ((Const) right).getValue() == 1) {
            return left;
        }
        if (left.equals(right)) {
            return new Const(1);
        }
        return new Division(left, right);
    }
}
