package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class Plus extends BinaryOp {
    public Plus(Expression left, Expression right) {
        super(left, right);
    }
    protected BigDecimal calc(BigDecimal left, BigDecimal right) {
        return left.add(right);
    }
}
