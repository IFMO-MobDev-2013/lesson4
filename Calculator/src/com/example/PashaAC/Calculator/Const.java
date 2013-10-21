package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class Const implements Expression {
    private final BigDecimal value;
    public Const(BigDecimal value) {
        this.value = value.setScale(30, BigDecimal.ROUND_HALF_EVEN);
    }
    public BigDecimal evaluate() {
        return value;
    }
}
