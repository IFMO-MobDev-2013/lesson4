package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class ExpressionMathParser {
    private String lexem;
    private Expression answer;
    private int pozition;
    private String expression;

    public ExpressionMathParser() {}
    public Expression ExpressionMathParser(String expression) throws Exception {
        this.expression = expression;
        if (expression.length() == 0)
            throw  new Exception("Error: Expression is empty.");
        pozition = 0;
        lexem = "";
        isGoodString();
        if (expression.charAt(expression.length() - 1) == '+' || expression.charAt(expression.length() - 1) == '-' ||
                expression.charAt(expression.length() - 1) == '*' || expression.charAt(expression.length() - 1) == '/' ||
                expression.charAt(expression.length() - 1) == '(')
            throw new Exception("Error: Syntax error");
        nextLexem();

        return plusMinusParser();
    }

    private void isGoodString() {
        if (expression.charAt(0) == '+' || expression.charAt(0) == '-')
            expression = "0" + expression;
        if (expression.charAt(0) == '*' || expression.charAt(0) == '/')
            expression = "1" + expression;
    }

    private Expression plusMinusParser() throws Exception{
        Expression temporaryVariable = multyDivisionParser();
        while (lexem.equals("+") || lexem.equals("-")) {
            String copy = lexem;
            nextLexem();
            if (copy.equals("+")) {
                temporaryVariable = new Plus(temporaryVariable, multyDivisionParser());
            }
            if (copy.equals("-")) {
                temporaryVariable = new Minus(temporaryVariable, multyDivisionParser());
            }
        }
        return temporaryVariable;
    }

    private Expression multyDivisionParser() throws Exception{
        Expression temporaryVariable = brackets();
        while (lexem.equals("*") || lexem.equals("/")) {
            String obr = lexem;
            nextLexem();
            if (obr.equals("*")) {
                temporaryVariable = new Multiplication(temporaryVariable, brackets());
            }
            if (obr.equals("/")) {
                temporaryVariable = new Division(temporaryVariable, brackets());
            }
        }
        return temporaryVariable;
    }

    private Expression brackets() throws Exception{
        Expression temporaryVariable;
        if (lexem.equals("(")) {
            int oldpozition = pozition;
            nextLexem();

            if (lexem.equals("+") || lexem.equals("-")) {
                lexem = "0";
                pozition = oldpozition;
                answer = new Const(new BigDecimal(lexem));
            }
            temporaryVariable = plusMinusParser();
            if (lexem.equals(")")) {
                nextLexem();
            }
            else {
                throw new Exception("Error: There is no close bracket.");
            }
        }
        else {
            if (lexem.equals(")")) {
                throw new Exception("Error: Close bracket before open bracket.");
            }
            temporaryVariable = answer;
            nextLexem();
        }
        return temporaryVariable;
    }

    private void nextLexem() throws Exception{
        lexem = "";
        if (pozition == expression.length()) return;
        boolean isWas = false;
        if (expression.charAt(pozition) == '+') {
            lexem = "+";
            pozition++;
            boolean negative = false;
            while (pozition < expression.length() && (expression.charAt(pozition) == '-' || expression.charAt(pozition) == '+')) {
                if (expression.charAt(pozition) == '-')
                    negative = !negative;
                pozition++;
            }
            if (pozition < expression.length() && expression.charAt(pozition) == '*' || expression.charAt(pozition) == '/')
                throw new Exception("Error: Syntax error.");
            pozition--;
            if (negative == true)
                lexem = "-";
            else
                lexem = "+";
            isWas = true;
        } else
        if (expression.charAt(pozition) == '-') {
            lexem = "-";
            pozition++;
            boolean negative = true;
            while (pozition < expression.length() && (expression.charAt(pozition) == '-' ||expression.charAt(pozition) == '+')) {
                if (expression.charAt(pozition) == '-')
                    negative = !negative;
                pozition++;
            }
            if (negative == true)
                lexem = "-";
            else
                lexem = "+";
            if (pozition < expression.length() && expression.charAt(pozition) == '*' || expression.charAt(pozition) == '/')
                throw new Exception("Error: Syntax error.");
            pozition--;
            isWas = true;
        } else
        if (expression.charAt(pozition) == '*') {
            lexem = "*";
            if (pozition + 1 < expression.length() && (expression.charAt(pozition + 1) == '*' ||
                    expression.charAt(pozition + 1) == '/'))
                throw new Exception("Error: Several operations in a row.");
            isWas = true;
        } else
        if (expression.charAt(pozition) == '/') {
            lexem = "/";
            if (pozition + 1 < expression.length() && (expression.charAt(pozition + 1) == '*' ||
                    expression.charAt(pozition + 1) == '/'))
                throw new Exception("Error: Several operations in a row.");
            isWas = true;
        } else
        if (expression.charAt(pozition) == '(') {
            lexem = "(";
            if (pozition + 1 < expression.length() && (expression.charAt(pozition + 1) == '*' ||
                    expression.charAt(pozition + 1) == '/'))
                throw new Exception("Error: Bad operations after open bracket.");
            isWas = true;
        } else
        if (expression.charAt(pozition) == ')') {
            lexem = ")";
            if (pozition + 1 < expression.length() && Character.isDigit(expression.charAt(pozition + 1)))
                throw new Exception("Error: Some digital symbol after close bracket.");
            isWas = true;
        }
        if (isWas == true) {
            pozition++;
            if (lexem != "+" && lexem != "-")
                return;
            if ((pozition - 2 >= 0 && (expression.charAt(pozition - 2) == '/' || expression.charAt(pozition - 2) == '*')) == false)
                return;
        }

        if ((Character.isDigit(expression.charAt(pozition)) || expression.charAt(pozition) == '.') == false) {
            throw new Exception("Error: Syntax error.");
        }

        boolean flag = false;
        boolean twoPoint = false;
        while (Character.isDigit(expression.charAt(pozition)) || expression.charAt(pozition) == '.') {
            lexem += expression.charAt(pozition);
            if (expression.charAt(pozition) == '.') {
                if (twoPoint == true) {
                    throw new Exception("Error: Several points in a row");
                }
                twoPoint = true;
            }
            flag = true;
            pozition++;
            if (pozition == expression.length()) break;
        }
        if (lexem.charAt(lexem.length() - 1) == '.')
            lexem = lexem + "0";

        if (pozition < expression.length() && expression.charAt(pozition) == '(')
            throw new Exception("Error: Open bracket after digital symbol.");
        if (flag == true) {
            answer = new Const(new BigDecimal(lexem));
            return;
        }
    }
}
