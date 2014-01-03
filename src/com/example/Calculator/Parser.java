package com.example.Calculator;

public class Parser {
    private String exp;
    private String cur_token = "END";
    private double cur_number;
    private String cur_name;
    private int pointer;

    public Parser(String s) {
        exp = s;
    }

    public Expression getExpr() {
        get_token();
        if (cur_token == "END") {
            return new Const(0);
        }
        return expr(false);
    }

    private void get_token() {
        while (pointer < exp.length() && exp.charAt(pointer) == ' ') {
            pointer++;
        }
        if (pointer == exp.length()) {
            cur_token = "END";
            return;
        }
        if (exp.charAt(pointer) >= '0' && exp.charAt(pointer) <= '9') {
            cur_number = exp.charAt(pointer) - '0';
            pointer++;
            while (pointer < exp.length() && exp.charAt(pointer) >= '0' && exp.charAt(pointer) <= '9') {
                cur_number *= 10;
                cur_number += exp.charAt(pointer) - '0';
                pointer++;
            }
            if(pointer < exp.length() && exp.charAt(pointer) == '.') {
                pointer++;
                double r = exp.charAt(pointer) - '0';
                double pw = 10;
                cur_number += r / pw;
                pointer++;
                while (pointer < exp.length() && exp.charAt(pointer) >= '0' && exp.charAt(pointer) <= '9') {
                    pw *= 10;
                    r = exp.charAt(pointer) - '0';
                    cur_number += r / pw;
                    pointer++;
                }
            }
            cur_token = "NUMBER";
            return;
        }

        if (exp.charAt(pointer) == '(') {
            cur_token = "LP";
        }
        if (exp.charAt(pointer) == ')') {
            cur_token = "RP";
        }
        if (exp.charAt(pointer) == '+') {
            cur_token = "PLUS";
        }
        if (exp.charAt(pointer) == '-') {
            cur_token = "MINUS";
        }
        if (exp.charAt(pointer) == '*') {
            cur_token = "TIMES";
        }
        if (exp.charAt(pointer) == '/') {
            cur_token = "DIVISION";
        }
        if (exp.charAt(pointer) == '%') {
            cur_token = "MOD";
        }
        pointer++;
        return;
    }

    private Expression prim(boolean get) {
        if (get)
            get_token();
        if (cur_token == "MINUS") {
            return new UnaryMinus(prim(true));
        }
        if (cur_token == "PLUS") {
            return new UnaryPlus(prim(true));
        }
        if (cur_token == "LP") {
            Expression expression = expr(true);
            get_token();
            return expression;
        }
        if (cur_token == "NUMBER") {
            Expression expression = new Const(cur_number);
            get_token();
            return expression;
        }

        return new Const(0);
    }

    private Expression term(boolean get) {
        Expression left = prim(get);
        while (true) {
            if (cur_token == "TIMES") {
                left = new Times(left, prim(true));
                continue;
            }
            if (cur_token == "MOD") {
                left = new Mod(left, prim(true));
                continue;
            }
            if (cur_token == "DIVISION") {
                left = new Division(left, prim(true));
                continue;
            }
            if (cur_token != "TIMES" || cur_token != "MOD" || cur_token != "DIVISION") {
                return left;
            }
        }
    }

    private Expression expr(boolean get) {
        Expression left = term(get);
        while (true) {
            if (cur_token == "PLUS") {
                left = new Plus(left, term(true));
                continue;
            }
            if (cur_token == "MINUS") {
                left = new Minus(left, term(true));
                continue;
            }
            if (cur_token != "PLUS" && cur_token != "MINUS") {
                return left;
            }
        }
    }
}