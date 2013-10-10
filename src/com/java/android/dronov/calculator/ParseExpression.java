package com.java.android.dronov.calculator;

import com.java.android.dronov.calculator.Exceptions.DivisionByZero;
import com.java.android.dronov.calculator.Exceptions.ParserException;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: dronov
 * Date: 08.10.13
 * Time: 0:44
 * To change this template use File | Settings | File Templates.
 */
public class ParseExpression {

    private Character[] operators = {'+', '-', '/', '*'};
    private String expression;
    private Double result;
    private String resultException = "";
    private Character token;
    private int currentToken = -1;

    public ParseExpression(String expression) {
        this.expression = expression;
        resultException = "";
        try {
            this.expression = transformUnary(expression) + "!";
            nextToken();
            checkRightExpression();
            result = readExpression();
        } catch (ParserException e) {
            resultException = "=Некорректный формат ввода выражения";
        } catch (DivisionByZero e) {
            resultException = "=Деление на ноль";
        }
    }

    private void checkRightExpression() {
        int currentBracketBalance = 0;
        for (int i = 0; i < expression.length() - 1; i++) {
            if (findOperator(expression.charAt(i)) && findOperator(expression.charAt(i + 1)))
                throw new ParserException();
        }

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(')
                currentBracketBalance++;
            if (expression.charAt(i) == ')')
                currentBracketBalance--;
        }
        if (currentBracketBalance != 0)
            throw new ParserException();
    }

    private String transformUnary(String result) {
        String newResult = "";
        result += "!";
        if (result.charAt(0) == '-' || result.charAt(0) == '+')
            newResult += "0";
        for (int i = 0; i < result.length() - 1; i++) {
            if (result.charAt(i) == ' ')
                continue;
            if (result.charAt(i) == '(' && (result.charAt(i + 1) == '+' || result.charAt(i + 1) == '-')) {
                newResult += "(0";
                continue;
            }
            newResult += result.charAt(i);
        }
        return newResult;
    }

    private Double readExpression() {
        Double result = readHighPriorityExpression();
        while (token == '+' || token == '-') {
            Character sign = token;
            nextToken();
            Double currentOperant = readHighPriorityExpression();
            if (sign == '+') {
                result += currentOperant;
            } else if (sign == '-') {
                result -= currentOperant;
            }
        }
        return result;
    }

    private Double readHighPriorityExpression() {
        Double result = calcExpression();
        while (token == '*' || token == '/') {
            Character sign = token;
            nextToken();
            Double currentOperant = calcExpression();
            if (sign == '*') {
                result *= currentOperant;
            } else if (sign == '/') {
                if (currentOperant == 0)
                    throw new DivisionByZero();
                result /= currentOperant;
            }
        }
        return result;
    }

    private Double calcExpression() {
        Double result = 0.0;
        if (token.equals('(')) {
            nextToken();
            result = readExpression();
            if (!token.equals(')'))
                throw new ParserException();
            nextToken();
        } else {
            String operand = "";
            while (token >= '0' && token <= '9' || token == '.') {
                operand += token;
                nextToken();
            }
            if (token != '(' && token != ')' && !findOperator(token) && token != '!')
                throw new ParserException();
            if (operand.isEmpty())
                throw new ParserException();
            return Double.parseDouble(operand);
        }
        return result;
    }

    private void nextToken() {
        currentToken++;
        token = expression.charAt(currentToken);
    }

    private boolean findOperator(Character operator) {
        for (Character operator1 : operators) {
            if (operator1.equals(operator))
                return true;
        }
        return false;
    }

    public String getResult() {
        if (!resultException.isEmpty())
            return resultException;
        else return result.toString();
    }

}
