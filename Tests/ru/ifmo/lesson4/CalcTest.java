package ru.ifmo.lesson4;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: asus
 * Date: 10.10.13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class CalcTest {
    private int testCount = 0;

    @Before
    public void setUp() throws Exception {

    }

    @Test
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

    private void checkTest(String countString, double result) {
        testCount++;
        String calc = "";
        try{
            calc = Calculator.evaluate(countString).toString();
        } catch (Exception ex){
            assertTrue(false);
        }

        assertEquals(Double.parseDouble(""+calc), result, 1e-7);
        assert true;
    }


}
