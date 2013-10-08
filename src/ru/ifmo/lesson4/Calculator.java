package ru.ifmo.lesson4;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 08.10.13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {
    final static int PRIORITY_SUM = 1;
    final static int PRIORITY_MUL = 2;
    static Number evaluate(String s) throws Exception{
        String s0 = s.replaceAll(" ", "");
        double result = evaluate(s0, 0, s0.length() - 1);
        if (Math.abs(result - Math.round(result)) < 1e-7){
            return (int) result;
        } else {
            return result;
        }
    }

    static Double evaluate(String s, int l, int r) throws Exception{
        int k = -1;
        int last_priority = 99;
        int b = 0;

        for (int i = l; i <= r; i++){
            if (s.charAt(i) == '(') b++;
            else if (s.charAt(i) == ')') b--;

            if (b == 0){
                if ((s.charAt(i) == '+' || s.charAt(i) == '-') && last_priority >= PRIORITY_SUM){
                    k = i;
                    last_priority = PRIORITY_SUM;
                }
                else if ((s.charAt(i) == 'x' || s.charAt(i) == '/') && last_priority >= PRIORITY_MUL){
                    k = i;
                    last_priority = PRIORITY_MUL;
                }
            }
        }

        if (k == -1){
            if (s.isEmpty()){
                return 0.0;
            } else if (s.charAt(l) == '(' && s.charAt(r) == ')'){
                return evaluate (s, l + 1, r - 1);
            }
            else if (s.charAt(l) >= '0' && s.charAt(l) <= '9'){
                String s0 = s.substring(l, r + 1);
                if (s0.indexOf('.', l) != -1){
                    return Double.parseDouble(s0);
                } else {
                    return (double)Integer.parseInt(s0);
                }
            }
            else {
                throw new Exception();
            }
        }
        else {
            if (s.charAt(k) == '+'){
                return evaluate(s, l, k - 1) + evaluate(s, k + 1, r);
            } else if (s.charAt(k) == '-'){
                return evaluate(s, l, k - 1) - evaluate(s, k + 1, r);
            } else if (s.charAt(k) == 'x'){
                return evaluate(s, l, k - 1) * evaluate(s, k + 1, r);
            } else if (s.charAt(k) == '/'){
                return evaluate(s, l, k - 1) / evaluate(s, k + 1, r);
            }
        }

        throw new Exception();
    }
}
