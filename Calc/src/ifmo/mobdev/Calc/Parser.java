package ifmo.mobdev.Calc;

public class Parser {
    private final String
        mul = "*/",
        sum = "+-",
        brack = "()",
        alpha = "0123456789.",
        chars = alpha + mul + sum + brack + " ";

    private void checkEmptiness(String expr) {
        if (expr.trim().isEmpty())
            throw new ParserFunctionException("empty string");
    }

    private boolean wrongOrder(char prev, char cur, int prevPos, int curPos) {
        return
                (mul.indexOf(prev) != -1 && mul.indexOf(cur) != -1) ||
                (alpha.indexOf(prev) != -1 && alpha.indexOf(cur) != -1 && curPos - prevPos > 1) ||
                (alpha.indexOf(prev) != -1 && cur == '(') ||
                (prev == ')' && alpha.indexOf(cur) != -1) ||
                (cur == ')' && alpha.indexOf(prev) == -1 && brack.indexOf(prev) == -1) ||
                (sum.indexOf(prev) != -1 && mul.indexOf(cur) != -1);
    }

    private void checkOperandsOrder(String expr) {
        if (chars.indexOf(expr.charAt(0)) == -1)
            throw new ParserFunctionException("unknown symbol:" + expr.charAt(0));

        char prevSymb = expr.charAt(0);
        int prevPos = 0;
        for (int i = 1; i < expr.length(); i++) {
            char curSymb = expr.charAt(i);

            if (chars.indexOf(curSymb) == -1)
                throw new ParserFunctionException("unknown symbol:" + expr.charAt(i));
            if (curSymb == ' ') continue;
            if (wrongOrder(prevSymb, curSymb, prevPos, i))
                throw new ParserFunctionException("wrong operands order");

            prevSymb = curSymb;
            prevPos = i;
        }
    }

    public Function parseExpression(String expr) {
        checkEmptiness(expr);
        checkOperandsOrder(expr);
        return lowPrior(expr, false);
    }

    private int skipUnary(String expr, int pos) {
        int i = pos;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (c == ' ' || c == '+' || c == '-')
                i++;
            else
                return i;
        }
        return i;
    }

    private int skipBrackets(String expr, int i) {
        if (expr.charAt(i++) == '(') {
            int balance = 1;
            while (balance > 0 && i < expr.length()) {
                if (expr.charAt(i) == '(')
                    balance++;
                else if (expr.charAt(i) == ')')
                    balance--;
                i++;
            }
            if (balance != 0)
                throw new ParserFunctionException("not enough brackets");
        }
        return i;
    }

    private int skipFirstTerm(String expr) {
        int i = 0;
        boolean mulMet = true;
        while (true) {
            if (mulMet) {
                i = skipUnary(expr, i);
                mulMet = false;
            }
            if (i >= expr.length())
                return -1;

            i = skipBrackets(expr, i); // i now has next position or first position after close bracket
            if (i >= expr.length())
                return -1;
            // c is digit or bracket or mulOperand
            char c = expr.charAt(i);
            if (c == '-' || c == '+')
                return i;
            else if (mul.indexOf(c) != -1) {
                mulMet = true;
                i++;
            }

        }
    }

    private Function lowPrior(String expr, boolean inversion) {
        checkEmptiness(expr);
        int pos = skipFirstTerm(expr);
        if (pos != -1) {
            Function left = highPrior(expr.substring(0, pos));
            String rest = expr.substring(pos + 1);
            if (expr.charAt(pos) == '+')
                if (!inversion)
                    return new Plus(left, lowPrior(rest, false));
                else
                    return new Minus(left, lowPrior(rest, false));
            else
                if (!inversion)
                    return new Minus(left, lowPrior(rest, true));
                else
                    return new Plus(left, lowPrior(rest, true));

        }
        return highPrior(expr);
    }

    private Function highPrior(String expr) {
        checkEmptiness(expr);
        int i = 0;
        while(i < expr.length()) {
            i = skipBrackets(expr, i);
            if (i >= expr.length()) break;
            if (expr.charAt(i) == '*')
                return new Times(bracketPrior(expr.substring(0, i)), highPrior(expr.substring(i + 1)));
            else if (expr.charAt(i) == '/')
                return new Division(bracketPrior(expr.substring(0, i)), highPrior(expr.substring(i + 1)));
        }
        return bracketPrior(expr);
    }

    private Function bracketPrior(String expr) {
        checkEmptiness(expr);
        char first = expr.charAt(0);
        if (first == ' ' || first == '+')
            return bracketPrior(expr.substring(1));
        else if (first == '-')
            return new Times(new Const(-1.0), bracketPrior(expr.substring(1)));
        else if (first == '(') {
            expr = expr.trim();
            return lowPrior(expr.substring(1, expr.length() - 1), false);
        }
        return varPrior(expr);
    }

    private Function varPrior(String expr) {
        checkEmptiness(expr);
        try {
            return new Const(Double.parseDouble(expr));
        } catch (NumberFormatException e) {
            throw new ParserFunctionException("incorrect double: " + expr);
        }
    }
}