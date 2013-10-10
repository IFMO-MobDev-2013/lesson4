package mazinva.Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Pattern numberWithoutLeadingZeroPattern = Pattern.compile("[1-9]+[0-9]*");
    private Pattern numberWithMaybeLeadingMinusPattern = Pattern.compile("-?[0-9]+");
    private Pattern numberPattern = Pattern.compile("[0-9]+");
    private Matcher matcher;

    public Expression parse(String s) throws SyntaxException {
        if (s.length() == 0) {
            throw  new SyntaxException("Can't parse empty string");
        }

        Result result = plusMinus(s);
        if (!result.rest.isEmpty()) {
            throw  new SyntaxException("Incorrect input. Can't parse '" + result.rest + "'");
        }
        return result.exp;
    }

    private Result plusMinus(String s) throws SyntaxException {
        Result current = mulDiv(s);
        Expression exp = current.exp;

        while (current.rest.length() > 0) {
            char sign = current.rest.charAt(0);
            if (sign != '+' && sign != '-') {
                break;
            }

            String next = current.rest.substring(1);
            current = mulDiv(next);

            if (sign == '+') {
                exp = new Plus(exp, current.exp);
            } else {
                exp = new Minus(exp, current.exp);;
            }
        }
        return new Result(exp, current.rest);
    }

    private Result mulDiv(String s) throws SyntaxException {
        Result current = brackets(s);
        Expression exp = current.exp;

        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if (sign != '*' && sign != '/') {
                return current;
            }

            String next = current.rest.substring(1);
            Result right = brackets(next);

            if (sign == '*') {
                exp = new Times(exp, right.exp);
            } else {
                exp = new Division(exp, right.exp);
            }

            current = new Result(exp, right.rest);
        }
    }

    private Result brackets(String s) throws SyntaxException {
        if ("".equals(s)) {
            throw new SyntaxException("Incorrect expression");
        }

        char ch = s.charAt(0);
        if (ch == '(') {
            Result r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                throw new SyntaxException("Not closed bracket");
            }
            return r;
        }

        return unaryOper(s);
    }

    private Result unaryOper(String s) throws SyntaxException {
        if ((s.length() > 0) && (s.charAt(0) == '+' || s.charAt(0) == '-')) {

            String next = s.substring(1);
            Result current = brackets(next);

            if (s.charAt(0) == '+') {
                return new Result(new Plus(new Const(0), current.exp), current.rest);
            } else {
                return new Result(new Minus(new Const(0), current.exp), current.rest);
            }
        } else {
            return num(s);
        }
    }

    private Result num(String s) throws SyntaxException  {
        int i = 0;

        while (i < s.length() && (Character.isDigit(s.charAt(i))
                                    || (s.charAt(i) == '.' && i > 0)
                                    || (s.charAt(i) == 'E' && i > 0) )) {
            i++;
        }

        if (i == 0) {
            throw new SyntaxException("Can't get valid number in '" + s + "'" );
        }

        String currentString = s.substring(0, i);

        String[] stringsSplitedByDot = currentString.split("\\.");
        if ((stringsSplitedByDot.length > 2) || (currentString.charAt(i - 1) == '.')) {

            throw new SyntaxException("Can't get valid number in '" + currentString + "'" );
        } else {
            matcher = numberWithoutLeadingZeroPattern.matcher(stringsSplitedByDot[0]);
            if (!matcher.matches()) {
                throw new SyntaxException("Can't get valid number in '" + currentString + "'" );
            }

            if (stringsSplitedByDot.length == 2) {
                String[] stringsSplitedByExp = stringsSplitedByDot[1].split("E");
                if ((stringsSplitedByExp.length > 2) || (currentString.charAt(i - 1) == 'E')) {

                    throw new SyntaxException("Can't get valid number in '" + currentString + "'" );
                } else {
                    matcher = numberPattern.matcher(stringsSplitedByExp[0]);
                    if (!matcher.matches()) {
                        throw new SyntaxException("Can't get valid number in '" + currentString + "'" );
                    }
                    if (stringsSplitedByExp.length == 2) {
                       matcher = numberWithMaybeLeadingMinusPattern.matcher(stringsSplitedByExp[1]);
                        if (!matcher.matches()) {
                            throw new SyntaxException("Can't get valid number in '" + s + "'" );
                        }
                    }
                }
            }
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        String restPart = s.substring(i);

        return new Result(new Const(dPart), restPart);
    }

}
