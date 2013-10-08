package com.polarnick.polaris.math.expressionParser;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 07.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class ExpressionParser {

    private static final String INCORRECT_BRACKETS = "Incorrect brackets!";
    private static final String ILLEGAL_EXPRESSION = "Illegal expression!";

    public static Evaluable parseExpression(String expression) {
        int n = expression.length();
        int[] pairBracket = new int[n];
        {
            int[] stackOfOpenBrackets = new int[n];
            int lastInStack = -1;
            for (int i = 0; i < n; i++) {
                if (expression.charAt(i) == '(') {
                    lastInStack++;
                    stackOfOpenBrackets[lastInStack] = i;
                } else if (expression.charAt(i) == ')') {
                    assertArgument(lastInStack >= 0, INCORRECT_BRACKETS);
                    pairBracket[stackOfOpenBrackets[lastInStack]] = i;
                    pairBracket[i] = stackOfOpenBrackets[lastInStack];
                    lastInStack--;
                }
            }
        }
        return parseSum(0, n, expression, pairBracket);
    }

    private static final List<Character> LEGAL_CHARACTERS_IN_SUM = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '*', '/');

    private static Evaluable parseSum(int from, int to, String expression, int[] pairBracket) {
        for (int i = to - 1; i >= from; i--) {
            if (expression.charAt(i) == ')') {
                i = pairBracket[i];
                assertArgument(expression.charAt(i) == '(', INCORRECT_BRACKETS);
            } else if (expression.charAt(i) == '-') {
                if (i == from) {
                    return new UnaryMinus(parseMultiplication(i + 1, to, expression, pairBracket));
                } else {
                    return new Minus(parseSum(from, i, expression, pairBracket),
                            parseMultiplication(i + 1, to, expression, pairBracket));
                }
            } else if (expression.charAt(i) == '+') {
                if (i == from) {
                    return new UnaryPlus(parseMultiplication(i + 1, to, expression, pairBracket));
                } else {
                    return new Plus(parseSum(from, i, expression, pairBracket),
                            parseMultiplication(i + 1, to, expression, pairBracket));
                }
            } else {
                assertArgument(LEGAL_CHARACTERS_IN_SUM.contains(expression.charAt(i)), ILLEGAL_EXPRESSION);
            }
        }
        return parseMultiplication(from, to, expression, pairBracket);
    }

    private static final List<Character> LEGAL_CHARACTERS_IN_MULTIPLICATION = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.');

    private static Evaluable parseMultiplication(int from, int to, String expression, int[] pairBracket) {
        for (int i = to - 1; i >= from; i--) {
            if (expression.charAt(i) == ')') {
                i = pairBracket[i];
                assertArgument(expression.charAt(i) == '(', INCORRECT_BRACKETS);
            } else if (expression.charAt(i) == '/') {
                return new Divide(parseMultiplication(from, i, expression, pairBracket),
                        parseMultiplication(i + 1, to, expression, pairBracket));
            } else if (expression.charAt(i) == '*') {
                return new Multiply(parseMultiplication(from, i, expression, pairBracket),
                        parseMultiplication(i + 1, to, expression, pairBracket));
            } else {
                assertArgument(LEGAL_CHARACTERS_IN_MULTIPLICATION.contains(expression.charAt(i)), ILLEGAL_EXPRESSION);
            }
        }
        return parseValue(from, to, expression, pairBracket);
    }

    private static Evaluable parseValue(int from, int to, String expression, int[] pairBracket) {
        assertArgument(expression.length() > from, ILLEGAL_EXPRESSION);
        if (expression.charAt(from) == '(') {
            assertArgument(pairBracket[from] == to - 1 && expression.charAt(to - 1) == ')', ILLEGAL_EXPRESSION);
            return parseSum(from + 1, to - 1, expression, pairBracket);
        }
        final String stringValue = expression.substring(from, to);
        try {
            double value = Double.parseDouble(stringValue);
            return new Constant(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal number: \"" + stringValue + "\"!");
        }
    }

    private static void assertArgument(boolean assertion, String message) {
        if (!assertion) {
            throw new IllegalArgumentException(message);
        }
    }
}