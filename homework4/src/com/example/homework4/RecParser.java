package com.example.homework4;

public class RecParser {
    public static Expression parse(String s) throws ParsException {

        if (s.length() == 0)
            throw new ParsException("invalid expression");


        for (int i = s.length() - 1; i > 0; --i) {             // reverse order

            if (i < 0) {
                throw new ParsException("invalid input");
            }

            if (s.charAt(i) == ')') {
                i = skipBrackets(s.substring(0, i));
            }
            if (i <= 0) {
                break;
            }
            if (s.charAt(i) == '+') {
                String s1 = s.substring(0, i);
                String s2 = reduceBrackets(s.substring(i + 1, s.length()));
                return new Plus(
                        parse(s1),
                        parse(s2)
                );
            }

            if (s.charAt(i) == '-') {
                String s1 = reduceBrackets(s.substring(0, i));
                String s2 = s.substring(i + 1, s.length());
                return new Minus(
                        parse(s1),
                        parse(s2)
                );
            }
        }



        for (int i = s.length() - 1; i > 0; --i) {
            if (s.charAt(i) == ')')
                i = skipBrackets(s.substring(0, i));
            if (i < 0)
                return parse(reduceBrackets(s));
            if (s.charAt(i) == '*') {

                String s1 = reduceBrackets(s.substring(0, i));
                String s2 = s.substring(i + 1, s.length());
                return new Times(
                        parse(s1),
                        parse(s2)
                );
            }

            if (s.charAt(i) == '/') {
                String s1 = s.substring(0, i);
                String s2 = reduceBrackets(s.substring(i + 1, s.length()));
                return new Division(
                        parse(s1),
                        parse(s2)
                );
            }

            if (s.charAt(i) == '%') {
                String s1 = reduceBrackets(s.substring(0, i));
                String s2 = s.substring(i + 1, s.length());
                return new Mod(
                        parse(s1),
                        parse(s2)
                );
            }
        }
        // here must be either the number or a unary operator

        if (isDouble(s)) {
            try {
                return new Const(Double.parseDouble(s));
            } catch (Exception exc) {
                throw new ParsException("can't parse");
            }
        } else {
            if (s.charAt(0) == '-')
                return new Minus(
                        new Const(0.0),
                        new Const(Double.parseDouble(s.substring(1)))
                );

            if (s.charAt(0) == '+') {
                return new Const(Double.parseDouble(s.substring(1)));
            }

            throw new ParsException("can't parse");
        }
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static String reduceBrackets(String s) {
        if(skipBrackets(s.substring(0, s.length()-1))!=-1)
            return s;
        int i = 0;
        while (s.charAt(i) == '(' && s.charAt(s.length() - i - 1) == ')')
            i++;
        return s.substring(i, s.length() - i);
    }

    private static int skipBrackets(String s) {
        int depth = -1;
        int i = s.length()-1;

        if (i <= 0) return 0;

        do {
            if (s.charAt(i) == '(') {
                ++depth;
            }
            if (s.charAt(i) == ')') {
                --depth;
            }
            i--;
        } while (i >= 0 && depth < 0);
        return i;
    }
}