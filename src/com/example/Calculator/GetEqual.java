package com.example.Calculator;

public class GetEqual {
    public static String s;
    public GetEqual(String s1) {
        s = s1;
        find();
    }

    public static void find() {
        Parser parser = new Parser(s);
        Expression e = parser.getExpr();
        Expression esimple = e.simplify();
        s = esimple.toString();
    }

    public String s() {
        return s;
    }
}
