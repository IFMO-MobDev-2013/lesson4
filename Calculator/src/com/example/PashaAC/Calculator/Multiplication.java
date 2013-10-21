package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class Multiplication extends BinaryOp {
    public Multiplication(Expression left, Expression right) {
        super(left, right);
    }
    protected BigDecimal calc(BigDecimal left, BigDecimal right) {
        return left.multiply(right);
    }
}
