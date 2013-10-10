package com.example.lesson4;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created with IntelliJ IDEA.
 * User: satori
 * Date: 10/7/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Parser {
    public static String[] tokens;
    private static final String DELIM = "[()+/*-]";
    private static final String regex = "((?<=%1$s)|(?=%1$s))";
    private static final String[][] precedence = {{"+", "-"},{"*","/"}};
    private static ArrayList<Integer> unaryOps;
//    private static ArrayList<Integer> binOPs1;
//    private static ArrayList<Integer> binOPs2;
    private static double parseTerm (String s) throws ParseException {
        if (s.matches("\\d+")) {
            return Double.parseDouble(s);
        }
        else {
            throw new ParseException(s + " is not a proper literal", 0);
        }
    }

    public static double parse(String s) {
        s = s.replaceAll("\\s+", "");
        tokens = s.split(String.format(regex,DELIM));
        if (tokens[0].equals("")) {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        }
        unaryOps = findUnaryOps();
//      binOPs1 = findBinaryOpPrecedence(0);
//      binOPs2 = findBinaryOpPrecedence(1);
        double d;

        d = parseTokens(0, tokens.length);






        return d;
    }
    private static double parseTokens(int start, int end) {
        int i1 = findLastOpPrec(0, start, end);
        int i2 = findLastOpPrec(1, start, end);
        if (i1 != end) {
            String op = tokens[i1];
            return op.equals("+") ? parseTokens(start, i1) + parseTokens(i1 + 1, end) : parseTokens(start, i1) - parseTokens(i1 + 1, end);
        }
        else if (i2 != end) {
            String op = tokens[i2];
            return op.equals("*") ? parseTokens(start, i2) * parseTokens(i2 + 1, end) : parseTokens(start, i2) / parseTokens(i2 + 1, end);
        }
        else {
            if (end - start == 1) {

                    return Double.parseDouble(tokens[start]);

            }
            else {
                String startoken = tokens[start];
                if (startoken.equals("+")) {
                    return parseTokens(start + 1, end);
                }
                else if (startoken.equals("-")) {
                    return -parseTokens(start + 1, end);
                }
                else {
                    return parseTokens(start + 1, end - 1);
                }


            }



        }



    }
    private static int findLastOpPrec(int prec, int start, int end) {
        int ans = end;
        int bracket_count = 0;
        for (int i = start; i < end; i++) {
            if (!unaryOps.contains(i)) {
                if (tokens[i].equals("(")) {
                    bracket_count++;
                }
                else if (tokens[i].equals(")")) {
                    bracket_count--;
                }
                else {
                    for (int j = 0; j < precedence[prec].length; j++) {
                        if (tokens[i].equals(precedence[prec][j])) {
                            if (bracket_count == 0 && i < end) {
                                ans = i;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }
    private static ArrayList<Integer> findBinaryOpPrecedence(int prec) {
        ArrayList<Integer> ops = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < precedence[prec].length; j++) {
                if (tokens[i].equals(precedence[prec][j])) {
                    ops.add(i);
                    break;
                }
            }
        }
        return ops;

    }
    private static ArrayList<Integer> findUnaryOps() {
        //int count = 0;
        ArrayList<Integer> al = new ArrayList<>();
        for(int i = 0; i < tokens.length; i++) {
            if ((tokens[i].equals("+") || tokens[i].equals("-")) &&
                    (i == 0 || tokens[i - 1].equals("(")
                            || tokens[i - 1].equals("+")
                            || tokens[i - 1].equals("-")
                            || tokens[i - 1].equals("/")
                            || tokens[i - 1].equals("*"))) {
                al.add(i);
            }
        }
        return al;
    }
}

