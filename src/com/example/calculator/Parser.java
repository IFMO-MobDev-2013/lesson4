package com.example.calculator;


import java.text.ParseException;

enum Lexem {PLUS, MINUS, MUL, DIV, OPEN, CLOSE, CONST, END, ERROR}

public class Parser {
    private static Lexem currentLexem;
    private static String expr;
    private static double valueOfConst;
    public static boolean parseError;
    public static boolean divisionByZero;

    public static double getValue(String expression) {
        expr = expression;
        valueOfConst = 0;
        parseError = false;
        divisionByZero = false;
        nextLexem();
        double a = getExpression();
        if (currentLexem != Lexem.END) parseError = true;
        return a;
    }

    private static double getExpression()  {
        double a = getSum();
        while ((currentLexem == Lexem.PLUS) || (currentLexem == Lexem.MINUS)) {
            Lexem sign = currentLexem;
            nextLexem();
            if (sign == Lexem.PLUS) {
                a = a + getSum();
            } else {
                    a = a - getSum();
            }
        }
        return a;
    }

    private static double getSum() {
        double a = getProd();
        while ((currentLexem == Lexem.MUL) || (currentLexem == Lexem.DIV)) {
            Lexem sign = currentLexem;
            nextLexem();
            if (sign.equals(Lexem.MUL)) {
                a = a * getProd();
            } else {
                double t = getProd();
                if (t != 0) {
                    a = a / t;
                } else divisionByZero = true;
            }
        }

        return a;
    }

    private static double getProd() {
        double a;
        switch (currentLexem) {
            case OPEN:
                nextLexem();
                a = getExpression();
                if (currentLexem == Lexem.CLOSE) nextLexem();
                else parseError = true;
                return a;
            case CONST:
                a = valueOfConst;
                nextLexem();
                return a;

            case PLUS:
                nextLexem();
                a = valueOfConst;
                nextLexem();
                return a;

            case MINUS:
                nextLexem();
                a = -valueOfConst;
                nextLexem();
                return a;
            default:
//                System.out.println(expr + "cyrr=" + currentLexem);
                parseError = true;
                return 0;

        }
    }

    private static void nextLexem() {

        while ((expr.length() > 0) && (expr.charAt(0) == ' ')) {
            expr = expr.substring(1);
        }

        if ((expr.length() > 0) && (expr.charAt(0) >= '0') && (expr.charAt(0) <= '9')) {
            currentLexem = Lexem.CONST;
            String n = "";
            int countOfPoints = 0;
            while ((expr.length() > 0) && (((expr.charAt(0) >= '0') && (expr.charAt(0) <= '9')) || (expr.charAt(0) == '.'))) {
                n = n + (expr.charAt(0) + "");
                if (expr.charAt(0) == '.') {
                    countOfPoints++;
                }
                expr = expr.substring(1);
            }
            if (countOfPoints > 1) {
                parseError = true;
                currentLexem = Lexem.ERROR;
            } else {
            valueOfConst = Double.parseDouble(n);
            }
        } else {
            if (expr.length() > 0) {
                char tmp = expr.charAt(0);
                switch (tmp) {
                    case '+':
                        currentLexem = Lexem.PLUS;
                        break;
                    case '-':
                        currentLexem = Lexem.MINUS;
                        break;
                    case '*':
                        currentLexem = Lexem.MUL;
                        break;
                    case '/':
                        currentLexem = Lexem.DIV;
                        break;
                    case '(':
                        currentLexem = Lexem.OPEN;
                        break;
                    case ')':
                        currentLexem = Lexem.CLOSE;
                        break;
                    default:
                        parseError = true;
                        currentLexem = Lexem.ERROR;
                        break;
                }
                expr = expr.substring(1);
            } else {
                currentLexem = Lexem.END;
            }
        }

    }
}
