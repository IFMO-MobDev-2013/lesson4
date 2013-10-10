package com.example.Calculator;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


public class CalculatorTest extends TestCase {

    private Calculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        assertEquals(calculator.getValue("2+2"), "4");
    }

    @Test
    public void testSub() throws Exception {
        assertEquals(calculator.getValue("25 - 7"), "18");
    }

    @Test
    public void testMul() throws Exception {
        assertEquals(calculator.getValue("5 * 5"), "25");
    }

    @Test
    public void testDiv() throws Exception {
        assertEquals(calculator.getValue("21 / 7"), "3");
    }

    @Test
    public void testHardExpression() throws Exception {
        assertEquals(calculator.getValue("2+3*12-4*(2+3+5/4)*(12+3-4*0.5)/12*149-(16*2)"), "-4029,41667");
    }

    @Test
    public void testSimpleExpression() throws Exception {
        assertEquals(calculator.getValue("-23 + 8 * 3 - 1"), "0");
    }

    @Test
    public void testUnaryMinus() throws Exception{
        assertEquals(calculator.getValue("-1 - (-2)"), "1");

    }

    @Test
    public void testUnaryPlus() throws Exception{
        assertEquals(calculator.getValue("+1 - (+2)"), "-1");

    }

    @Test
     public void testDivByZero() throws Exception{
        try {
            calculator.getValue("21 / 0");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Division by zero");
        }

    }

    @Test
    public void testSyntaxError1() throws Exception{
        int flag = 0;
        try {
            calculator.getValue("(4 / 5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Syntax error");
            flag = 1;
        }
        assertEquals(1, flag);
    }

    @Test
    public void testSyntaxError2() throws Exception{
        int flag = 0;
        try {
            calculator.getValue("4 / 5)");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Syntax error");
            flag = 1;
        }
        assertEquals(1, flag);
    }

    @Test
    public void testSyntaxError3() throws Exception{
        int flag = 0;
        try {
            calculator.getValue("4.)");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Syntax error");
            flag = 1;
        }
        assertEquals(flag, 1);
    }

    @Test
    public void testSyntaxError4() throws Exception{
        int flag = 0;
        try {
            calculator.getValue("4-*5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Syntax error");
            flag = 1;
        }
        assertEquals(1, flag);
    }

    @Test
    public void testSyntaxError5() throws Exception{
        int flag = 0;
        try {
            calculator.getValue("4-*5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Syntax error");
            flag = 1;
        }
        assertEquals(flag, 1);
    }

    @Test
    public void testSyntaxError6() throws Exception{
        int flag = 0;
        try {
            calculator.getValue("*5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Syntax error");
            flag = 1;
        }
        assertEquals(flag, 1);
    }


}
