package com.example.homework4;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: slavian
 * Date: 10.10.13
 * Time: 8:46
 * To change this template use File | Settings | File Templates.
 */


public class CalcUnitTest extends Assert{

    final double eps = 0.000001;

    @Test
    public void testExpression() {
        double a, b;
        a = Math.random() * 100;
        b = Math.random() * 100+1;


        assertEquals(new Const(a).evaluate(), a, eps);

        assertEquals(
            new Plus(
                new Const(a),
                new Const(b)
            ).evaluate(),
            a + b,
            eps
        );
        assertEquals(
                new Minus(
                        new Const(a),
                        new Const(b)
                ).evaluate(),
                a - b,
                eps
        );
        assertEquals(
                new Times(
                        new Const(a),
                        new Const(b)
                ).evaluate(),
                a * b,
                eps
        );
        assertEquals(
                new Division(
                        new Const(a),
                        new Const(b)
                ).evaluate(),
                a / b,
                eps
        );
        assertEquals(
                new Mod(
                        new Const(a),
                        new Const(b)
                ).evaluate(),
                a % b,
                eps
        );
    }

    private void checkParser(String s, double value) {
        try
        {
            double res = RecParser.parse(s).evaluate();
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
