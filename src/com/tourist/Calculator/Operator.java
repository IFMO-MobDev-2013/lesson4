package com.tourist.Calculator;

public enum Operator {
    PLUS     { double evaluate(double x, double y) { return x + y; } },
    MINUS    { double evaluate(double x, double y) { return x - y; } },
    TIMES    { double evaluate(double x, double y) { return x * y; } },
    DIVISION { double evaluate(double x, double y) { return x / y; } };

    abstract double evaluate(double x, double y);
}
