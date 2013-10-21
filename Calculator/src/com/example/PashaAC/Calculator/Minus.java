package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class Minus extends BinaryOp {
    public Minus(Expression left, Expression right) {
        super(left, right);
    }
    protected BigDecimal calc(BigDecimal left, BigDecimal right) {
        return left.subtract(right);
    }
}
