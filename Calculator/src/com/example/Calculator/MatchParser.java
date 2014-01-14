package com.example.Calculator;

public class MatchParser {

    private static class Result
    {
        double acc;
        public String rest;
        public Result(double v, String r) {
            this.acc = v;
            this.rest = r;
        }
    }

    public static double parse(String s) throws Exception {
        Result result = plusMinus(s);
        if (!result.rest.isEmpty()) {
            System.err.println("Error while parsing");
        }
        return result.acc;
    }

    private static Result plusMinus(String s) throws Exception {
        Result current = mulDiv(s);
        double acc = current.acc;
        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) {
		        break;
	        }
            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);
            current = mulDiv(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
        }
        return new Result(acc, current.rest);
    }

    private static Result bracket(String s) throws Exception {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Result r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                System.err.println("Error: no closing bracket");
            }
            return r;
        }
        return num(s);
    }
    
    private static Result mulDiv(String s) throws Exception {
        Result current = bracket(s);

        double acc = current.acc;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/')) return current;

            String next = current.rest.substring(1);
            Result right = bracket(next);

            if (sign == '*') {
                acc *= right.acc;
            } else {
	        if (sign == '/') {
                    if (right.acc != 0) {
                        acc /= right.acc;
                    } else {
                        throw new Exception("division by zero");
                    }
                }
	    }
            current = new Result(acc, right.rest);
        }
    }

   private static Result num(String s) throws Exception {
        boolean negative = false;
        if (s.charAt(0) == '-' ) {
            negative = true;
            s = s.substring(1);
        } else {
            if (s.charAt(0) == '+') {
                negative = false;
                s = s.substring(1);
            }
        }
	    int i = 0;
        int dot_cnt = 0;
	    while (i < s.length() && (Character.isDigit(s.charAt(i)) || (s.charAt(i) == '.'))) {
	        if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("illegal expression");
            }
            i++;
	    }
        if (i == 0) {
            throw new Exception("illegal expression");
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if (negative) {
            dPart = -dPart;
        }
        String restPart = s.substring(i);
        return new Result(dPart, restPart);
        }

} 