package com.example.Calculator;

import java.util.ArrayList;
import java.util.List;

class Node {
    List<Node> child;
    double value;
    TypeNode type;
    int sign;

    Node(TypeNode type, int sign, double value) {
        this.type = type;
        this.sign = sign;
        this.value = value;
        child = new ArrayList<Node>();

    }

    Node(TypeNode type, int sign) {
        this.type = type;
        this.sign = sign;
        child = new ArrayList<Node>();
        value = 0;
    }

    public double getValue() throws Exception {

        if (TypeNode.factor == type) {
            return child.get(0).getValue();
        }
        if (TypeNode.sum == type) {
            double result = 1;
            for (Node t : child) {
                double subResult = t.getValue();
                switch (t.sign) {
                    case '*': {
                        result *= subResult;
                        break;
                    }
                    default: {
                        if (subResult == 0) {
                            throw new Exception("Division by zero");
                        }
                        result /= subResult;
                    }
                }

            }
            return sign * result;
        }
        if (TypeNode.expression == type) {
            double result = 0;
            for (Node t : child) {
                double subResult = t.getValue();
                result += subResult;
            }
            return sign * result;
        }

        return value;


    }

    void add(Node node) {
        child.add(node);
    }
}