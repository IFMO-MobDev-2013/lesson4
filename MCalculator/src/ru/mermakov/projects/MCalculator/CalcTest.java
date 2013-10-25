package ru.mermakov.projects.MCalculator;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 09.10.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class CalcTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    private void checkOperations(String parseString, double result) {
        String res = "";
        try {
            res = Double.toString(new Parser().evaluate(parseString));
        } catch (Exception ex) {
            assertTrue(false);
        }
        assertEquals(Double.parseDouble(res), result, 0);
    }

    private void checkExceptions(String parseString) {
        boolean throwEx = false;
        try {
            String res = Double.toString(new Parser().evaluate(parseString));
        } catch (ParserException pe) {
            throwEx = true;
        }
        assertTrue(throwEx);
    }

    public void runTest() {
        checkOperations("0", 0);
        checkOperations("1+10", 11);
        checkOperations("-50", -50);
        checkOperations("1-50", -49);
        checkOperations("2*2", 4);
        checkOperations("48/8", 6);
        checkOperations("+5", +5);
        checkOperations("168-(255+149*(-15/5))+209*-331", -68819);
        checkExceptions(")(");
        checkExceptions("9/0");
        checkExceptions("");
        checkExceptions("9+8)");
    }
}
