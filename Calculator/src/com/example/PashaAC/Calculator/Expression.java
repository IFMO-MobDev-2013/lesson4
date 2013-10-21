package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public interface Expression {
    BigDecimal evaluate() throws Exception;
}