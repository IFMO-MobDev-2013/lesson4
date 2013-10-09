package com.example.lesson4;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Ruslan
 * Date: 09.10.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */

public class Parser {
    public class Token {
        double acc;
        String rest;

        public Token(double acc, String rest) {
            this.acc = acc;
            this.rest = rest;
        }
    }

    private Token plusMinus(String s) {
        Token current = mulDiv(s);
        double acc = current.acc;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) {
                break;
            }

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            current = mulDiv(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
        }
        return new Token(acc, current.rest);
    }

    private Token bracket(String s) {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Token r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            }
            return r;
        }
        return num(s);
    }

    private Token mulDiv(String s) {
        Token current = bracket(s);
        double acc = current.acc;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/')) {
                return current;
            }

            String next = current.rest.substring(1);
            Token right = bracket(next);

            if (sign == '*') {
                acc *= right.acc;
            } else {
                if (right.acc == 0) {
                    throw new ArithmeticException("Division By ZERO");
                }
                acc /= right.acc;
            }

            current = new Token(acc, right.rest);
        }
    }

    private Token num(String s) {
        int i = 0;
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        }
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            i++;
        }
        double number = Double.parseDouble(s.substring(0, i));
        if (negative) {
            number = -number;
        }
        String restPart = s.substring(i);

        return new Token(number, restPart);


    }

    public double parse(String s) throws ArithmeticException {
        return plusMinus(s.replace(" ", "")).acc;
    }
}

