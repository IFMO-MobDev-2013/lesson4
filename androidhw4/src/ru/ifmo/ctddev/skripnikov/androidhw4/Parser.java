package ru.ifmo.ctddev.skripnikov.androidhw4;

import ru.ifmo.ctddev.skripnikov.androidhw4.BinaryOperation.Operation;

class Parser {
    private static String expression;
    private static int index;

    public static Evaluable parse(String expression) throws ParseException {
        Parser.expression = expression.replaceAll("\\s+", "") + "!";
        index = 0;
        return formula(true);
    }

    private static Evaluable formula(boolean isFirst)
            throws ParseException {
        Evaluable e = factor(false);
        char nextChar = getChar();
        while (nextChar != '!' && nextChar != ')') {
            if (nextChar == '*' || nextChar == '/') {
                returnChar();
                e = term(e);
            } else if (nextChar == '+' || nextChar == '-') {
                returnChar();
                e = expression(e);
            } else {
                throw new ParseException(expression, index);
            }
            nextChar = getChar();
        }
        if (isFirst && nextChar == ')') {
            throw new ParseException(expression, index);
        }
        returnChar();
        return e;
    }

    private static char getChar() {
        return expression.charAt(index++);
    }

    private static void returnChar() {
        index--;
    }

    private static Evaluable getConst(boolean negativeSign) throws ParseException {
        int start = index, end = index;
        char c = getChar();
        while (Character.isDigit(c) || c == '.' || c == 'E' || c == 'e') {
            if (c == 'E' || c == 'e') {
                c = getChar();
                end++;
                if(c == '!')
                    throw new ParseException(expression, index);
            }
            c = getChar();
            end++;
        }
        returnChar();
        try {
            double value = Double.parseDouble(expression.substring(start, end));
            if (negativeSign) {
                return new Const(-value);
            } else {
                return new Const(value);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(expression, index);
        }
    }

    private static Evaluable factor(boolean negativeSign) throws ParseException {
        char nextChar = getChar();
        if (Character.isDigit(nextChar) || nextChar == '.') {
            returnChar();
            return getConst(negativeSign);
        } else if (nextChar == '(') {
            Evaluable result = formula(false);
            if (getChar() == ')') {
                if (negativeSign) {
                    return new BinaryOperation(new Const(0.0),
                            result, Operation.MINUS);
                } else {
                    return result;
                }
            }
            throw new ParseException(expression, index);
        } else if (nextChar == '-') {
            return factor(!negativeSign);
        } else if (nextChar == '+') {
            return factor(negativeSign);
        }
        throw new ParseException(expression, index);
    }

    private static Evaluable term(Evaluable left)
            throws ParseException {
        char nextChar = getChar();
        Evaluable right;
        if (nextChar != '*' && nextChar != '/') {
            returnChar();
            return left;
        }
        right = factor(false);
        if (nextChar == '*') {
            return term(new BinaryOperation(left, right, Operation.TIMES));
        } else {
            return term(new BinaryOperation(left, right, Operation.DIVISION));
        }
    }

    private static Evaluable expression(Evaluable left) throws ParseException {
        char nextChar = getChar();
        Evaluable right;
        if (nextChar != '+' && nextChar != '-') {
            returnChar();
            return left;
        }
        right = factor(false);
        right = term(right);
        if (nextChar == '+') {
            return expression(new BinaryOperation(left, right,
                    Operation.PLUS));
        }
        return expression(new BinaryOperation(left, right, Operation.MINUS));
    }
}
