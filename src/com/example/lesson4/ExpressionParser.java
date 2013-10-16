package com.example.lesson4;

/**
 * Created with IntelliJ IDEA.
 * User: Genyaz
 * Date: 10.10.13
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionParser {

    public static Double parseExpression(String s) {
        if (s.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        } else {
            double next_koef = 1;
            double result = 0;
            int last_pos = 0;
            if (s.charAt(0) == '-') next_koef = -1;
            if ((s.charAt(0) == '-') || (s.charAt(0) == '+')) last_pos = 1;
            int level = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') level++;
                if (s.charAt(i) == ')') level--;
                if ((level == 0) && (i > 0)) {
                    if ((s.charAt(i) == '+') || (s.charAt(i) == '-')) {
                        if ((s.charAt(i - 1) != '/') && (s.charAt(i - 1) != '*')) {
                            result += next_koef * parseTerm(s.substring(last_pos, i));
                            if (s.charAt(i) == '+') {
                                next_koef = 1;
                            } else {
                                next_koef = -1;
                            }
                            last_pos = i + 1;
                        }
                    }
                }
            }
            result += next_koef * parseTerm(s.substring(last_pos, s.length()));
            return new Double(result);
        }
    }

    private static Double parseTerm(String s) {
        if (s.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        } else {
            int level = 0;
            int last_pos = 0;
            boolean mul = true;
            double result = 1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') level++;
                if (s.charAt(i) == ')') level--;
                if (level == 0) {
                    if ((s.charAt(i) == '*') || (s.charAt(i) == '/')) {
                        if (mul) {
                            result *= parseFactor(s.substring(last_pos, i));
                        } else {
                            result /= parseFactor(s.substring(last_pos, i));
                        }
                        mul = (s.charAt(i) == '*');
                        last_pos = i + 1;
                    }
                }
            }
            if (mul) {
                result *= parseFactor(s.substring(last_pos, s.length()));
            } else {
                result /= parseFactor(s.substring(last_pos, s.length()));
            }
            return new Double(result);
        }
    }

    private static Double parseFactor(String s) {
        if (s.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        } else {
            if ((s.charAt(0) == '+') || (s.charAt(0) == '-')) {
                double koef = 1;
                if (s.charAt(0) == '-') koef = -1;
                return new Double(koef * parseFactor(s.substring(1, s.length())));
            } else {
                if ((s.charAt(0) == '(') && (s.charAt(s.length() - 1) == ')')) {
                    return parseExpression(s.substring(1, s.length() - 1));
                } else {
                    return Double.parseDouble(s);
                }
            }
        }

    }
}
