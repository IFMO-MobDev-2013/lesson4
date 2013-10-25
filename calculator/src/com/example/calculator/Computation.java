package com.example.calculator;
import java.util.*;
import java.lang.Exception;


public class Computation {


    private static ArrayList<Double> stack = new ArrayList<Double>();
    private static ArrayList<Character> operations = new ArrayList<Character>();
    private static ArrayList<Boolean> unary = new ArrayList<Boolean>();

    static private boolean is_op(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    static private int priority(char op) {
        if (op < 0) {
            return 4;
        }
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return -1;
    }

    static private void process_op(char op, boolean un) {
        if (un == true) {
            double l = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            if (op == '+') {
                stack.add(l);
            }
            if (op == '-') {
                stack.add(-l);
            }
        } else {
            double r = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            double l = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            switch (op) {
                case '+':
                    stack.add(l + r);
                    break;
                case '-':
                    stack.add(l - r);
                    break;
                case '*':
                    stack.add(l * r);
                    break;
                case '/':
                    stack.add(l / r);
                    break;
            }
        }
    }

    static private double  get_variable_val(String s) {
        double value = 0;
        value = Double.parseDouble(s);
        return value;
    }

    static private boolean isUnary(char c) {
        return (c == '-' || c == '+');
    }

    static private double  calc(String s) throws Exception{
        boolean may_unary = true;
        operations.clear();
        unary.clear();
        stack.clear();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            char nextc = '0';
            if (i < len - 1)
                nextc = s.charAt(i+1);
            if (c == '(') {
                if (i < len - 1 && (nextc == '.' || nextc == ')' || nextc == '*' || nextc == '/')) {
                    throw new Exception("Error");
                }
                operations.add('(');
                unary.add(false);
                may_unary = true;
            } else if (s.charAt(i) == ')') {     //digital and ,
                if (i < len - 1 && (Character.isDigit(nextc) || nextc == ',')) {
                    throw new Exception("Error");
                }
                while (operations.get(operations.size() - 1) != '(') {
                    process_op(operations.get(operations.size() - 1), unary.get(unary.size() - 1));
                    operations.remove(operations.size() - 1);
                    unary.remove(unary.size() - 1);
                }
                operations.remove(operations.size() - 1);
                unary.remove(unary.size() - 1);
                may_unary = false;
            } else if (is_op(s.charAt(i))) {
                if (i < len - 1 && (nextc == '.' || nextc == ')' || nextc == '*' || nextc == '/')) {
                    throw new Exception("Error");
                }
                char curop = s.charAt(i);
                boolean un = false;
                if (may_unary && isUnary(curop)) {
                    un = true;
                }

                while ((operations.size() > 0)
                        && ((un == false && priority(operations.get(operations.size() - 1)) >= priority(curop))
                        || (un == true && priority(operations.get(operations.size() - 1)) > priority(curop)))) {
                    process_op(operations.get(operations.size() - 1), unary.get(unary.size() - 1));
                    operations.remove(operations.size() - 1);
                    unary.remove(unary.size() - 1);
                }
                operations.add(curop);
                unary.add(un);
                may_unary = true;
            } else {
                String operand = "";
                while (i < s.length() && (s.charAt(i) == '.' || Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                    operand = operand + s.charAt(i);
                    i++;
                }
                i--;
                stack.add(get_variable_val(operand));
                may_unary = false;
            }
        }
        while (operations.size() > 0) {
            process_op(operations.get(operations.size() - 1), unary.get(unary.size() - 1));
            operations.remove(operations.size() - 1);
            unary.remove(unary.size() - 1);
        }
        return stack.get(stack.size() - 1);
    }

    static public double  getRes(String s) throws Exception {
        return calc(s);
    }
}
