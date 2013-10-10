package com.example.Calculator;

import java.util.HashMap;

public class Variable extends Expression {
    private String key;

    public Variable(String a) {
        key = a;
    }

    public Double evaluate(HashMap<String, Double> map) {
        return map.get(key);
    }

    public String toString() {
        return key;
    }

    String getKey() {
        return key;
    }

    public Expression simplify() {
        return this;
    }

    public boolean equals(Expression e) {
        return (e instanceof Variable && ((Variable) e).getKey().equals(key));
    }

}
