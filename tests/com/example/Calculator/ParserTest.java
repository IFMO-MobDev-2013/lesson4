package com.example.Calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static final double DELTA = 0.0000000001;


    private void testCase(String str, double res) {
        Parser parser = new Parser();
        Double ans = 0.0;
        try {
            ans = parser.operationParser(str);
        } catch (CalculationException e) {
            e.printStackTrace();
        }
        assertEquals(res, ans, DELTA);
    }

    @Test
    public void testMinus() {
        testCase("56-70", -14);
    }

    @Test
    public void testPlus() {
        testCase("45+30", 75);
    }

    @Test
    public void testYnMinus() {
        testCase("-34-(-54)-6", 14);
    }

    @Test
    public void testYnPlus() {
        testCase("+34+(+54)", 88);
    }

    @Test
    public void testTimes() {
        testCase("34*23", 782);
    }

    @Test
    public void testDivision() {
        testCase("25/10", 2.5);
    }

    @Test
    public void testBr() {
        testCase("-(56-78)+(4-5)/7", 21.8571428571);
    }

    @Test
    public void testNull() {
        testCase("0*4.5-56*0+0-0", 0);
    }

    @Test
    public void testBigtest() {
        testCase("-(6*(-8)+5-72/6-4+(-6/1))", 65);
    }
}
