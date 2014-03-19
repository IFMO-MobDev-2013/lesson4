package com.example.Calculator;

import java.text.DecimalFormat;


public class Calculator {
    String expr;
    Node root;

    Calculator() {
        this.expr = "";
        this.root = null;
    }

    public String getValue(String expr) throws Exception {
        this.expr = expr;
        this.root = new Node(TypeNode.expression, 1);
        getExpression(root, 0, expr.length() - 1);

        DecimalFormat df = new DecimalFormat("#.#####");
        return df.format(root.getValue());
    }



    private void getSum(Node root, int start, int finish) throws Exception {
        int curPosition = start;
        char currentAction = '*';
        int count = 0;

        if (start > finish) {
            throw new Exception("Syntax error");
        }
        while (expr.charAt(curPosition) == ' ') {
            curPosition++;
            if (curPosition == finish + 1) {
                throw new Exception("Syntax error");
            }
        }

        for (int i = curPosition; i <= finish; i++) {
            if (expr.charAt(i) == '(') {
                count++;
            }
            if (expr.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                throw new Exception("Syntax error");
            }
            if ((count == 0) && ((expr.charAt(i) == '*') || (expr.charAt(i) == '/'))) {
                if (curPosition > i - 1) {
                    throw new Exception("Syntax error");
                }
                Node v = new Node(TypeNode.factor, currentAction);
                root.add(v);
                getSum(v, curPosition, i - 1);
                currentAction = expr.charAt(i);
                curPosition = i + 1;
            }
        }
        if (curPosition > finish) {
            throw new Exception("Syntax error");
        }
        Node v = new Node(TypeNode.factor, currentAction);
        root.add(v);
        getFactor(v, curPosition, finish);
        if (count > 0) {
            throw new Exception("Syntax error");
        }

    }

    private void getFactor(Node root, int start, int finish) throws Exception {
        int curPosition = start;

        if (start > finish) {
            throw new Exception("Syntax error");
        }
        while (expr.charAt(curPosition) == ' ') {
            curPosition++;
            if (curPosition == finish + 1) {
                throw new Exception("Syntax error");
            }
        }

        if (expr.charAt(curPosition) == '+') {
            Node v = new Node(TypeNode.factor, '*');
            root.add(v);
            getSum(v, curPosition + 1, finish);
            return;
        }
        if (expr.charAt(curPosition) == '-') {
            Node v = new Node(TypeNode.sum, -1);
            root.add(v);
            getSum(v, curPosition + 1, finish);
            return;
        }

        if (expr.charAt(curPosition) == '(') {
            int i = finish;
            while (expr.charAt(i) != ')') {
                if (expr.charAt(i) != ' ') {
                    throw new Exception("Syntax error");
                }
                i--;
                if (i < curPosition) {
                    throw new Exception("Syntax error");
                }
            }
            Node v = new Node(TypeNode.expression, 1);
            root.add(v);
            getExpression(v, curPosition + 1, i - 1);
            return;
        }

        String s = expr.substring(curPosition, finish + 1).trim();


        double value = Double.parseDouble(s);

        Node v = new Node(TypeNode.val, 1, value);
        root.add(v);


    }

    private void getExpression(Node root, int start, int finish) throws Exception {

        int curPosition = start;
        int currentSign = 1;
        int count = 0;

        if (start > finish) {
            throw new Exception("Syntax error");
        }
        while (expr.charAt(curPosition) == ' ') {
            curPosition++;
            if (curPosition == finish + 1) {
                throw new Exception("Syntax error");
            }
        }
        if (expr.charAt(curPosition) == '+') {
            curPosition++;
        }
        if (expr.charAt(curPosition) == '-') {
            curPosition++;
            currentSign *= -1;
        }

        if (curPosition == finish + 1) {
            throw new Exception("Syntax error");
        }
        for (int i = curPosition; i <= finish; i++) {
            if (expr.charAt(i) == '(') {
                count++;
            }
            if (expr.charAt(i) == ')') {
                count--;
            }
            if (count < 0) {
                throw new Exception("Syntax error");
            }
            if ((count == 0) && (expr.charAt(i) == '+')) {
                if (curPosition > i - 1) {
                    throw new Exception("Syntax error");
                }

                Node v = new Node(TypeNode.sum, currentSign);
                root.add(v);
                getSum(v, curPosition, i - 1);
                curPosition = i + 1;
                currentSign = 1;

            }
            if ((count == 0) && (expr.charAt(i) == '-')) {
                if (curPosition > i - 1) {
                    throw new Exception("Syntax error");
                }

                Node v = new Node(TypeNode.sum, currentSign);
                root.add(v);
                getSum(v, curPosition, i - 1);
                currentSign = -1;
                curPosition = i + 1;
            }

        }
        if (curPosition > finish) {
            throw new Exception("Syntax error");
        }
        Node v = new Node(TypeNode.sum, currentSign);
        root.add(v);
        getSum(v, curPosition, finish);
        if (count > 0) {
            throw new Exception("Syntax error");
        }
    }
}
