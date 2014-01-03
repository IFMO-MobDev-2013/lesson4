package com.example.Calculator;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    private static final double DELTA = 0.0000000001;

    @Test
    public void testUnaryMinus() {
        testCase("-5", -5);
    }

    @Test
    public void testUnaryPlus() {
        testCase("-4", -4);
    }

    @Test
    public void testPlus() {
        testCase("5+34+435345", 5+34+435345);
    }

    @Test
    public void testMinus() {
        testCase("345-23-554-45345", 345-23-554-45345);
    }

    @Test
    public void testTimes() {
        testCase("3423*34*2", 3423*34*2);
    }

    @Test
    public void testTimesDouble() {
        testCase("4.343*34.7", 4.343*34.7);
    }

    @Test
    public void testTimesDouble2() {
        testCase("0.00034*0.00000001*2",0.00034*0.00000001*2);
    }

    @Test
    public void testDiv() {
        testCase("10/2",5);
    }

    @Test
    public void testDiv2() {
        testCase("10/3",10.0/3);
    }

    @Test
    public void testComplexEx() {
        testCase("13748/342*(34+7*243)-((6-3)*4)",13748.0/342*(34+7*243)-((6-3)*4));
    }

    @Test
    public void testComplexEx2() {
        testCase("4+6*(45-34)*(56*(45*56))*45.0/5*(6)",4+6*(45-34)*(56*(45*56))*45.0/5*(6));
    }


    @Test
    public void testSuperComplexExpression() {
        testCase("(239.017*124)+15.0/6", (239.017*124)+15.0/6);
    }


    private void testCase(String s, double res) {
        Parser parser = new Parser(s);
        assertEquals(res, parser.getExpr().evaluate(null), DELTA);
    }

}
