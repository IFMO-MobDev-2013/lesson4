package com.example.Calculator;

import java.util.HashMap;

public abstract class Expression {
    public abstract Double evaluate(HashMap<String, Double> map);

    public abstract String toString();

    public abstract Expression simplify();

    public abstract boolean equals(Expression e);
}
