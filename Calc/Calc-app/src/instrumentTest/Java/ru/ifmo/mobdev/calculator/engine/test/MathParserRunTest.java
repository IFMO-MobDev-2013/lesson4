package ru.ifmo.mobdev.calculator.engine.test;

import org.junit.Test;
import org.junit.Assert;

import ru.ifmo.mobdev.calculator.engine.matchparserrun.MathParserRun;

/**
 * Created by Nick Smelik on 15.12.13.
 */
public class MathParserRunTest extends Assert {

    final double eps = 0.000001;

    private void checkParser(String s, double value) {
        try
        {
            double res = MathParserRun.evaluate(s);
            Assert.assertEquals(res, value, eps);
        }
        catch(Exception exc)
        {
            // the test will fail anyway if something goes wrong
        }
    }

    @Test
    public void testInteger()
    {
        checkParser("123", 123);
    }

    @Test
    public void testDouble()
    {
        checkParser("123.123", 123.123);
    }

    @Test
    public void testAddition()
    {
        checkParser("2+2", 4);
        checkParser("0+2", 2);
        checkParser("0+0", 0);
    }

    @Test
    public void testSubtraction()
    {
        checkParser("22-11", 11);
        checkParser("11-22", -11);
        checkParser("22-22", 0);
    }

    @Test
    public void testUnaryPlus()
    {
        checkParser("+2.2", 2.2);
    }

    @Test
    public void testUnaryMinus()
    {
        checkParser("-2.2", -2.2);
    }

    @Test
    public void testTimes()
    {
        checkParser("11*11", 121);
        checkParser("1.2*6", 7.2); // a very funny test
    }

    @Test
    public void testDivision()
    {
        checkParser("2/2", 1);
        checkParser("2/1", 2);
        checkParser("10/4", 2.5);
    }

    @Test
    public void testMod()
    {
        checkParser("5%2", 1);
        checkParser("6%2", 0);
    }

    @Test
    public void testMultipleTimesAndDivision()
    {
        checkParser("2*2*3*9/3*7", 2*2*3*9/3*7);

        checkParser("2*2*3*9/3*0*7*17/5*4", 0);
    }

    @Test
    public void testMultipleAdditionAndSubtraction()
    {
        checkParser("2+0+2+0+3+2+7-1-1-1-1-1+12-4+3-2", 2+0+2+0+3+2+7-1-1-1-1-1+12-4+3-2);
    }

    @Test
    public void testParentheses()
    {
        checkParser("(-2+3)*7-(+5)", (-2+3)*7-(+5));
        checkParser("((2+10)/2%5+2)*3", ((2+10)/2%5+2)*3);
    }

    @Test
    public void testLargeExpression()
    {
        checkParser("(5-(22.11*16)+(13-22))/21+123.2-15%16+(2+(3+(4+(2*(3.33*5)))))-12.5*82.123",
                (5-(22.11*16)+(13-22))/21+123.2-15%16+(2+(3+(4+(2*(3.33*5)))))-12.5*82.123);
    }
}
