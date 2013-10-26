package com.blumonk.Calc.Parser;

import android.util.Log;

public class Parser {
    private static String target;
    private static String currToken;
    private static int position;

    public static Evaluable parse(String expression) throws ParseException {
        position = 0;
        expression = expression.replaceAll("\\s+", "");
        expression = properExpr(expression);
        Log.d("WHAT", expression);
        target = expression;
        currToken = nextToken();
		return expr();
    }

    private static String properExpr(String expression) {
        String buff = new String();
        if (expression.charAt(0) == '+' || expression.charAt(0) == '-') {
            buff = "0";
        }
        for (int i = 0; i < expression.length() - 1; i++) {
            buff = buff + expression.charAt(i);
            if (expression.charAt(i) == '(' && (expression.charAt(i + 1) == '-' || expression.charAt(i + 1) == '+')) {
                buff = buff + '0';
            }
        }
        buff = buff + expression.charAt(expression.length() - 1);
        return buff;
    }

    private static String nextToken() throws ParseException {
        int pos = position;
        int end = pos + 1;
        String lexem = "";
        while (end <= target.length()) {
            lexem = target.substring(pos, end);
            try {
                Double.parseDouble(lexem);
                ++end;
            } catch (Exception e) {
                if (lexem.length() == 1) {
                    position = end;
                    return lexem;
                } else {
                    position = end - 1;
                    return lexem.substring(0, lexem.length() - 1);
                }
            }
        }
        return lexem;
    }

    private static Evaluable expr() throws ParseException {
        Evaluable result = plus();
        while (currToken.equals("+") || currToken.equals("-")) {
            if (currToken.equals("+")) {
                currToken = nextToken();
                result = new BinOperation(result, plus(), BinaryOperation.PLUS);
            } else {
                currToken = nextToken();
                result = new BinOperation(result, plus(), BinaryOperation.MINUS);
            }
        }
        currToken = nextToken();
        return result;
    }

    private static boolean isConst(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Evaluable plus() throws ParseException {
        Evaluable result = mul();
        while (currToken.equals("*") || currToken.equals("/") || currToken.equals("%")) {
            if (currToken.equals("*")) {
                currToken = nextToken();
                result = new BinOperation(result, mul(), BinaryOperation.TIMES);
	        } else if (currToken.equals("/")) {
                currToken = nextToken();
                result = new BinOperation(result, mul(), BinaryOperation.DIVISION);
            } else if (currToken.equals("%")) {
            	currToken = nextToken();
                result = new BinOperation(result, mul(), BinaryOperation.MOD);
            }
        }
        return result;
    }

    private static Evaluable mul() throws ParseException {
        Evaluable result = pow();
        while (currToken.equals("^")) {
            currToken = nextToken();
            result = new BinOperation(result, pow(), BinaryOperation.POWER);
        }
        return result;
    }

    private static Evaluable pow() throws ParseException {
        if (isConst(currToken)) {
            String ans = currToken;
            currToken = nextToken();
            return new Const(Double.parseDouble(ans));
        }
        if (currToken.equals("(")) {
            currToken = nextToken();
            return expr();
        }
        if (currToken.equals(")")) {
            currToken = nextToken();
            return new Const(1);
        }
        throw new ParseException("Parse error: " + currToken);
    }
}
