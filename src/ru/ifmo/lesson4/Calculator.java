package ru.ifmo.lesson4;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 08.10.13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {
    private final static int PRIORITY_SUM = 1;
    private final static int PRIORITY_MUL = 2;
    public static Number evaluate(String s) throws Exception{
        String s0 = s.replaceAll(" ", "");
        double result = evaluate(s0, 0, s0.length() - 1);
        if (Math.abs(result - Math.round(result)) < 1e-8){
            return (int) result;
        } else {
            return result;
        }
    }

    private static Double evaluate(String s, int l, int r) throws Exception{
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
            if (s.charAt(l) == '(' && s.charAt(r) == ')'){
                return evaluate (s, l + 1, r - 1);
            }
            else if (s.charAt(r) >= '0' && s.charAt(r) <= '9'){
                String s0 = s.substring(l, r + 1);
                //if (s0.indexOf('.', 0) != -1){
                    return Double.parseDouble(s0);
                /*} else {
                    return (double)Integer.parseInt(s0);
                } */
            }
            else {
                throw new Exception();
            }
        }
        else {
            if (s.charAt(k) == '+'){
                if (k == l){
                    return evaluate(s, k + 1, r);
                } else {
                    return evaluate(s, l, k - 1) + evaluate(s, k + 1, r);
                }
            } else if (s.charAt(k) == '-'){
                if (k == l){
                    return -evaluate(s, k + 1, r);
                } else {
                    return evaluate(s, l, k - 1) - evaluate(s, k + 1, r);
                }
            } else if (s.charAt(k) == 'x'){
                return evaluate(s, l, k - 1) * evaluate(s, k + 1, r);
            } else if (s.charAt(k) == '/'){
                return evaluate(s, l, k - 1) / evaluate(s, k + 1, r);
            }
        }

        throw new Exception();
    }
}

class CalcTest extends TestCase {
    private int testCount = 0;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    private void checkTest(String countString, double result) {
        testCount++;
        String calc = "";
        try{
            calc = Calculator.evaluate(countString).toString();
        } catch (Exception ex){
            assertTrue(false);
        }

        double k = Math.abs(Double.parseDouble(""+calc) - result);
        assertTrue("Test #" + testCount + " failed", k < 1e-7);
    }

    public void runTest(){
        checkTest("2x(1+3)/1", 8);
        checkTest("1+2+3-4/1+1/1", 3);
        checkTest("+3", 3);
        checkTest("-2", -2);
        checkTest("(+7)", 7);
        checkTest("3 x (-9)", -27);
        checkTest("-1+2", 1);
        checkTest("0.7+0.3", 1);
        checkTest(".7+. 3", 1);
        checkTest("2x(1+3)/1", 8);
        checkTest("2x(1+3)/1", 8);
        checkTest("5-2", 3);
        checkTest("2x(1+3)/1", 8);
        checkTest("(-2+(-3x(-1)))", 1);
        checkTest("10/3", 10/3.0);
        checkTest("(-2+(-3x(-1)-2)x5)/3", 1);
        checkTest("(5555555 + 4444444) / 2 x 2 + 1", 1e7);

   }

}
