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
        String n = "";
        int b = -1;
        int cnt = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '-') {
                if(b == -1)
                    b = i;
                cnt++;
            } else {
                if(b != -1) {
                    if(cnt % 2 == 0) {
                        n += '+';
                    } else {
                        n += '-';
                    }
                    b = -1;
                    cnt = 0;
                }
                n += s.charAt(i);
            }
        }
        s = n;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                return s;
        }
        find();
        return s;
    }
}
