package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class Division extends BinaryOp {
    public Division(Expression left, Expression right) {
        super(left, right);
    }
    protected BigDecimal calc(BigDecimal left, BigDecimal right) throws Exception {
        BigDecimal eps = new BigDecimal("0.0000000001");
        if (right.abs().min(eps) == right.abs())
            throw new Exception("Error: Division by zero.");
        return left.divide(right, BigDecimal.ROUND_HALF_EVEN).setScale(30, BigDecimal.ROUND_HALF_EVEN);
    }
}
