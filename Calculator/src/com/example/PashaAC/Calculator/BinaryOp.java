package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public abstract class BinaryOp implements Expression {
    protected Expression left;
    protected Expression right;
    protected BinaryOp(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public BigDecimal evaluate() throws Exception{
        return calc(left.evaluate(), right.evaluate());
    }
    protected abstract BigDecimal calc(BigDecimal left, BigDecimal right) throws Exception;
}
