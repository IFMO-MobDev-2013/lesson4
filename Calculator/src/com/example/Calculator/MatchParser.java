package com.example.Calculator;

public class MatchParser {

    private static class Result
    {
        Evaluable acc;
        public String rest;
        public Result(Evaluable v, String r) {
            this.acc = v;
            this.rest = r;
        }
    }

    public static Evaluable parse(String s) {
        Result result = plusMinus(s);
        return result.acc;
    }

    private static Result plusMinus(String s) {
        Result current = mulDiv(s);
        Evaluable acc = current.acc;
        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) {
		        break;
	        }
            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);
            current = mulDiv(next);
            if (sign == '+') {
                acc = new Plus(acc, current.acc);
            } else {
                acc = new Minus(acc, current.acc);
            }
        }
        return new Result(acc, current.rest);
    }

    private static Result bracket(String s) {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Result r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            }
            return r;
        }
        return num(s);
    }
    
    private static Result mulDiv(String s) {
        Result current = bracket(s);

        Evaluable acc = current.acc;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/')) return current;

            String next = current.rest.substring(1);
            Result right = bracket(next);

            if (sign == '*') {
                acc = new Times(acc, right.acc);
            } else {
	        if (sign == '/') {
                    acc = new Division(acc, right.acc);
                }
	    }
            current = new Result(acc, right.rest);
        }
    }

   private static Result num(String s)
   {
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
	    String s1 = "";
	    while (i < s.length() && (Character.isDigit(s.charAt(i)) || (s.charAt(i) == '.'))) {
	        s1 += s.charAt(i);
	        i++;
	    }
        s = s.substring(i);
        double dPart = Double.parseDouble(s1);
        if (negative) {
            dPart = -dPart;
        }
        Const temp = new Const(dPart);
        return new Result(temp, s);
        }


} 