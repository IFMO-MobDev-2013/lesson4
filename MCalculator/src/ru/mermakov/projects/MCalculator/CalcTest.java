package ru.mermakov.projects.MCalculator;

import android.app.AlertDialog;
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

    private void checkTest(String parseString, double result) {
        String res="";
        try{
            res= Double.toString(new Parser().evaluate(parseString));
        }
        catch (Exception ex){
            assertTrue(false);
        }
        assertEquals(Double.parseDouble(res),result,0);
    }

    public void runTest() {
        checkTest("0",0);
        checkTest("1+10",11);
        checkTest("-50",-50);
        checkTest("1-50",-49);
        checkTest("2*2",4);
        checkTest("48/8",6);
        checkTest("+5",+5);
        checkTest("168-(255+149*(-15/5))+209*-331",-68819);
    }
}
