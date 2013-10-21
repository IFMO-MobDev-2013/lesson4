package com.example.PashaAC.Calculator;

import java.math.BigDecimal;

public class ExpressionMathParser {
    private String lexem;
    private Expression answer;
    private int pozition;
    private String expression;

    public Expression ExpressionMathParser(String expression) throws Exception {
        this.expression = expression;
        pozition = 0;
        lexem = "";
        isGoodString();
        nextLexem();
        return plusMinusParser();
    }

    private void isGoodString() throws Exception{
        if (expression.length() == 0)
            throw  new Exception("Error: Expression is empty.");
        if (expression.charAt(0) == '*' || expression.charAt(0) == '/')
            throw new Exception("Error: Syntax error");
        if (expression.charAt(0) == '+' || expression.charAt(0) == '-')
            expression = "0" + expression;
        int bracketsInExpression = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ')')
                bracketsInExpression--;
            if (expression.charAt(i) == '(')
               bracketsInExpression++;
            if (bracketsInExpression < 0)
                throw new Exception("Error: Brackets error.");
        }
        if (bracketsInExpression != 0)
            throw new Exception("Error: Brackets error.");
        if (Character.isDigit(expression.charAt(expression.length() - 1)) == false && expression.charAt(expression.length() - 1) != ')' && expression.charAt(expression.length() - 1) != '.')
            throw new Exception("Error: Syntax error.");
    }

    private Expression plusMinusParser() throws Exception{
        Expression temporaryVariable = multyDivisionParser();
        while (lexem.equals("+") || lexem.equals("-")) {
            boolean negative = false;
            while (lexem.equals("+") || lexem.equals("-")) {
                if (lexem.equals("-"))
                    negative = !negative;
                nextLexem();
            }
            if (lexem.equals("*") || lexem.equals("/") || lexem.equals(")"))
                throw new Exception("Error: Syntax error.");
            if (negative == false) {
                temporaryVariable = new Plus(temporaryVariable, multyDivisionParser());
            }
            if (negative == true) {
                temporaryVariable = new Minus(temporaryVariable, multyDivisionParser());
            }
        }
        return temporaryVariable;

    }

    private Expression multyDivisionParser() throws Exception{
        Expression temporaryVariable = brackets();
        while (lexem.equals("*") || lexem.equals("/")) {
            String obr = lexem;
            boolean negative = false;
            nextLexem();
            while (lexem.equals("+") || lexem.equals("-")) {
                if (lexem.equals("-"))
                    negative = !negative;
                nextLexem();
            }
            if (lexem.equals("*") || lexem.equals("/") || lexem.equals(")"))
                throw new Exception("Error: Syntax error.");
            if (negative == true) {
                lexem = "-" + lexem;
                answer = new Const(new BigDecimal(lexem));
            }
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
            nextLexem();
            boolean negative = false;
            while (lexem.equals("+") || lexem.equals("-")) {
                if (lexem.equals("-"))
                    negative = !negative;
                nextLexem();
            }
            if (lexem.equals("*") || lexem.equals("/"))
                throw new Exception("Error: Several operations in a row.");
            if (lexem.equals(")"))
                throw new Exception("Error: Syntax error.");

            if (Character.isDigit(lexem.charAt(0)) && negative == true)
                answer = new Const(new BigDecimal("-" + lexem));
            if (Character.isDigit(lexem.charAt(0)) && negative == false)
                answer = new Const(new BigDecimal("+" + lexem));

            if (negative == true && lexem.equals("("))
                temporaryVariable = new Minus(new Const(new BigDecimal("0")),plusMinusParser());
            else
                temporaryVariable = plusMinusParser();
            if (lexem.equals(")")) {
                nextLexem();
                if (lexem.equals("(") || Character.isDigit(lexem.charAt(0)))
                    temporaryVariable = new Multiplication(temporaryVariable, plusMinusParser());
            }
        }
        else {
            temporaryVariable = answer;
            String oldLexem = lexem;
            nextLexem();
            if (Character.isDigit(oldLexem.charAt(0)) && lexem.equals("("))
                temporaryVariable = new Multiplication(temporaryVariable, plusMinusParser());

        }
        return temporaryVariable;
    }

    private void nextLexem() throws Exception{
        lexem = "";
        if (pozition == expression.length()) {
            lexem = "!";
            return;
        }
        if (expression.charAt(pozition) == '+') lexem = "+";
        if (expression.charAt(pozition) == '-') lexem = "-";
        if (expression.charAt(pozition) == '*') lexem = "*";
        if (expression.charAt(pozition) == '/') lexem = "/";
        if (expression.charAt(pozition) == '(') lexem = "(";
        if (expression.charAt(pozition) == ')') lexem = ")";
        if (lexem.length() > 0) {
            pozition++;
            return;
        }

        boolean twoPoint = false;
        while (Character.isDigit(expression.charAt(pozition)) || expression.charAt(pozition) == '.') {
            if (expression.charAt(pozition) == '.') {
                if (twoPoint == true) {
                    throw new Exception("Error: Several points in a row");
                }
                twoPoint = true;
            }
            lexem += expression.charAt(pozition);
            pozition++;
            if (pozition == expression.length()) break;
        }
        if (twoPoint == true && lexem.length() == 1)
            throw new Exception("Error: Syntax error.");
        if (lexem.charAt(lexem.length() - 1) == '.')
            lexem = lexem + "0";
        if (lexem.charAt(0) == '.')
            lexem = "0" + lexem;
        answer = new Const(new BigDecimal(lexem));
    }
}
