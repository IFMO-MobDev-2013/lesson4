package com.tourist.Calculator;

public class BinaryOperator implements Expression {
    public Expression left;
    public Expression right;
    public Operator type;

    public BinaryOperator(Expression left, Operator type, Expression right) {
        this.left = left;
        this.type = type;
        this.right = right;
    }

    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();
        return type.evaluate(leftValue, rightValue);
    }
}
