package ru.mermakov.projects.MCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 09.10.13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
public class Parser {
    private static final int NONE = 0;
    private static final int DELIMITER = 1;
    private static final int VARIABLE = 2;
    private static final int NUMBER = 3;

    private static final int SYNTAX_ERROR = 0;
    private static final int UNBAL_PARENS = 1;
    private static final int NO_EXP = 2;
    private static final int DIV_BY_ZERO = 3;
    private static final String EOF = "\0";

    private String exp;
    private int explds;
    private String token;
    private int tokType;

    public String toString() {
        return String.format("Exp = {0}\nexplds = {1}\nToken = {2}\nTokType = {3}", exp.toString(), explds,
                token.toString(), tokType);
    }

    private void getToken() {
        tokType = NONE;
        token = "";
        if (explds == exp.length()) {
            token = EOF;
            return;
        }
        while (explds < exp.length() && Character.isWhitespace(exp.charAt(explds)))
            ++explds;
        if (explds == exp.length()) {
            token = EOF;
            return;
        }
        if (isDelim(exp.charAt(explds))) {
            token += exp.charAt(explds);
            explds++;
            tokType = DELIMITER;
        } else if (Character.isLetter(exp.charAt(explds))) {
            while (!isDelim(exp.charAt(explds))) {
                token += exp.charAt(explds);
                explds++;
                if (explds >= exp.length())
                    break;
            }
            tokType = VARIABLE;
        } else if (Character.isDigit(exp.charAt(explds))) {
            while (!isDelim(exp.charAt(explds))) {
                token += exp.charAt(explds);
                explds++;
                if (explds >= exp.length())
                    break;
            }
            tokType = NUMBER;
        } else {
            token = EOF;
            return;
        }
    }

    private boolean isDelim(char charAt) {
        if ((" +-/*%^=()".indexOf(charAt)) != -1)
            return true;
        return false;
    }

    public double evaluate(String expstr) throws ParserException {
        double result;
        exp = expstr;
        explds = 0;
        getToken();
        if (token.equals(EOF))
            handleErr(NO_EXP);
        result = evalPlusMinus();
        if (!token.equals(EOF))
            handleErr(SYNTAX_ERROR);
        return result;
    }

    private double evalPlusMinus() throws ParserException {
        char op;
        double result;
        double partialResult;
        result = evalMultiDivide();
        while ((op = token.charAt(0)) == '+' ||
                op == '-') {
            getToken();
            partialResult = evalMultiDivide();
            switch (op) {
                case '-':
                    result -= partialResult;
                    break;
                case '+':
                    result += partialResult;
                    break;
            }
        }
        return result;
    }

    private double evalMultiDivide() throws ParserException {
        char op;
        double result;
        double partialResult;
        result = evalUnitPlusMinus();
        while ((op = token.charAt(0)) == '*' ||
                op == '/') {
            getToken();
            partialResult = evalUnitPlusMinus();
            switch (op) {
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if (partialResult == 0.0)
                        handleErr(DIV_BY_ZERO);
                    result /= partialResult;
                    break;
            }
        }
        return result;
    }

    private double evalUnitPlusMinus() throws ParserException {
        double result;
        String op;
        op = " ";
        if ((tokType == DELIMITER) && token.equals("+") ||
                token.equals("-")) {
            op = token;
            getToken();
        }
        result = evalBrackets();
        if (op.equals("-"))
            result = -result;
        return result;
    }

    private double evalBrackets() throws ParserException {
        double result;
        if (token.equals("(")) {
            getToken();
            result = evalPlusMinus();
            if (!token.equals(")"))
                handleErr(UNBAL_PARENS);
            getToken();
        } else
            result = atom();
        return result;
    }

    private double atom() throws ParserException {
        double result = 0.0;
        switch (tokType) {
            case NUMBER:
                try {
                    result = Double.parseDouble(token);
                } catch (NumberFormatException exc) {
                    handleErr(SYNTAX_ERROR);
                }
                getToken();

                break;
            default:
                handleErr(SYNTAX_ERROR);
                break;
        }
        return result;
    }

    private void handleErr(int nOEXP2) throws ParserException {

        String[] err = {
                "Syntax error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by zero"
        };
        throw new ParserException(err[nOEXP2]);
    }
}
