package com.tourist.Calculator;

public class ExpressionParser {
    private static String s;
    private static int pos;
    private static String token;

    public static String getToken() throws ParserException {
        if (pos < s.length()) {
            char c = s.charAt(pos);
            ++pos;
            if ("0123456789.E".indexOf(c) != -1) {
                int start = pos - 1;
                while (pos < s.length() && ("0123456789.E".indexOf(s.charAt(pos)) != -1 || s.charAt(pos - 1) == 'E')) {
                    ++pos;
                }
                int finish = pos;
                return s.substring(start, finish);
            }
            return "" + c;
        }
        return null;
    }

    public static Expression parseOperand() throws ParserException {
        token = getToken();
        if (token == null) {
            throw new ParserException("Expected operand, found end of expression");
        }
        if ("-".equals(token)) {
            return new BinaryOperator(new Const(0), Operator.MINUS, parseOperand());
        }
        if ("+".equals(token)) {
            return parseOperand();
        }
        if ("(".equals(token)) {
            Expression expr = parseFull();
            if (")".equals(token)) {
                token = getToken();
                return expr;
            }
            throw new ParserException("Expected \")\", found \"" + token + "\"");
        }
        if ("0123456789.E".indexOf(token.charAt(0)) != -1) {
            double value;
            try {
                value = Double.parseDouble(token);
            } catch (Exception e) {
                throw new ParserException("\"" + token + "\" is not a number");
            }
            token = getToken();
            return new Const(value);
        }
        throw new ParserException("Expected operand, found \"" + token + "\"");
    }

    public static Expression parseTerm() throws ParserException {
        Expression expr = parseOperand();
        while ("*".equals(token) || "/".equals(token)) {
            expr = new BinaryOperator(expr, "*".equals(token) ? Operator.TIMES : Operator.DIVISION, parseOperand());
        }
        return expr;
    }

    public static Expression parseFull() throws ParserException {
        Expression expr = parseTerm();
        while ("+".equals(token) || "-".equals(token)) {
            expr = new BinaryOperator(expr, "+".equals(token) ? Operator.PLUS : Operator.MINUS, parseTerm());
        }
        return expr;
    }

    public static Expression parse(String str) throws ParserException {
        s = str;
        pos = 0;
        Expression expr = parseFull();
        if (token != null) {
            throw new ParserException("Expected end of expression, found \"" + token + "\"");
        }
        return expr;
    }
}
