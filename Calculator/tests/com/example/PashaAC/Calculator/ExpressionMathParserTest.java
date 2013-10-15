package com.example.PashaAC.Calculator;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExpressionMathParserTest extends TestCase {
    private ExpressionMathParser testParser;
    private final double DELTA = 0.0000001;
    @Before
    public void setUp() throws Exception {
        testParser = new ExpressionMathParser();
    }

    @Test
    public void testNumber() throws Exception {
        assertEquals(5, Double.parseDouble(testParser.ExpressionMathParser("5").evaluate().toString()), DELTA);
        assertEquals(3, Double.parseDouble(testParser.ExpressionMathParser("3").evaluate().toString()), DELTA);
        assertEquals(1, Double.parseDouble(testParser.ExpressionMathParser("1").evaluate().toString()), DELTA);
    }

    @Test
    public void testPlus() throws Exception {
        assertEquals(5, Double.parseDouble(testParser.ExpressionMathParser("2+3").evaluate().toString()), DELTA);
        assertEquals(-2+-8, Double.parseDouble(testParser.ExpressionMathParser("-2+-8").evaluate().toString()), DELTA);
        assertEquals(54.2 + 16.03, Double.parseDouble(testParser.ExpressionMathParser("54.2+16.03").evaluate().toString()), DELTA);
        assertEquals(10, Double.parseDouble(testParser.ExpressionMathParser("2----+--3+--+--5").evaluate().toString()), DELTA);
    }

    @Test
    public void testMinus() throws Exception {
        assertEquals(2-3, Double.parseDouble(testParser.ExpressionMathParser("2-3").evaluate().toString()), DELTA);
        assertEquals(6, Double.parseDouble(testParser.ExpressionMathParser("-2--8").evaluate().toString()), DELTA);
        assertEquals(54.2 - 16.03, Double.parseDouble(testParser.ExpressionMathParser("54.2-16.03").evaluate().toString()), DELTA);
        assertEquals(5, Double.parseDouble(testParser.ExpressionMathParser("2--3").evaluate().toString()), DELTA);
        assertEquals(-25.0, Double.parseDouble(testParser.ExpressionMathParser("-(--(----25))").evaluate().toString()), DELTA);
    }

    @Test
    public void testMulti() throws Exception {
        assertEquals(2*3, Double.parseDouble(testParser.ExpressionMathParser("2*3").evaluate().toString()), DELTA);
        assertEquals(-2*3, Double.parseDouble(testParser.ExpressionMathParser("-2*3").evaluate().toString()), DELTA);
        assertEquals(-2*-3, Double.parseDouble(testParser.ExpressionMathParser("-2*-3").evaluate().toString()), DELTA);
        assertEquals(54.2 * 16.03, Double.parseDouble(testParser.ExpressionMathParser("54.2*16.03").evaluate().toString()), DELTA);
    }

    @Test
    public void testDivision() throws Exception {
        assertEquals(2.0/3.0, Double.parseDouble(testParser.ExpressionMathParser("2.0/3.0").evaluate().toString()), DELTA);
        assertEquals(-2.0/3.0, Double.parseDouble(testParser.ExpressionMathParser("-2.0/3.0").evaluate().toString()), DELTA);
        assertEquals(-2.0/-3.0, Double.parseDouble(testParser.ExpressionMathParser("-2.0/-3.0").evaluate().toString()), DELTA);
        assertEquals(54.2 / 16.03, Double.parseDouble(testParser.ExpressionMathParser("54.2/16.03").evaluate().toString()), DELTA);
        assertEquals(20/4, Double.parseDouble(testParser.ExpressionMathParser("20/4").evaluate().toString()), DELTA);
        assertEquals(5/(-1), Double.parseDouble(testParser.ExpressionMathParser("5/(-1)").evaluate().toString()), DELTA);
        assertEquals(23.123/65.123781, Double.parseDouble(testParser.ExpressionMathParser("23.123/65.123781").evaluate().toString()), 0.0000001);
    }

    @Test
    public void HardTests() throws Exception {
        assertEquals(2+3*12-4*(2+3+5.0/4.0)*(12+3-4*0.5)/12.0*149-(16*2), Double.parseDouble(testParser.ExpressionMathParser("2+3*12-4*(2+3+5/4)*(12+3-4*0.5)/12*149-(16*2)").evaluate().toString()), DELTA);
        assertEquals(2+3*12-4*(2+3+5.0/4)*(12+3-4*0.5), Double.parseDouble(testParser.ExpressionMathParser("2+3*12-4*(2+3+5/4)*(12+3-4*0.5)").evaluate().toString()), DELTA);
        assertEquals(2+3*12-4*(2+3+5.0/4), Double.parseDouble(testParser.ExpressionMathParser("2+3*12-4*(2+3+5/4)").evaluate().toString()), DELTA);
        assertEquals(-4*(2+3+5.0/4.0), Double.parseDouble(testParser.ExpressionMathParser("-4*(2+3+5.0/4.0)").evaluate().toString()), DELTA);
        assertEquals((+5.3-+4)/(-1.0), Double.parseDouble(testParser.ExpressionMathParser("(+5.3-+4)/(-1.0)").evaluate().toString()), DELTA);
        assertEquals(-3-(2-3*(4.3/1.123)), Double.parseDouble(testParser.ExpressionMathParser("-3-(2-3*(4.3/1.123))").evaluate().toString()), DELTA);
        assertEquals((((-3-(2-3-(2+3))))), Double.parseDouble(testParser.ExpressionMathParser("(((-3-(2-3-(2+3)))))").evaluate().toString()), DELTA);
        assertEquals(4.0, Double.parseDouble(testParser.ExpressionMathParser("(((-3-(2-3-(2+3)))))").evaluate().toString()), DELTA);
        assertEquals(1.0, Double.parseDouble(testParser.ExpressionMathParser("1/3*3").evaluate().toString()), DELTA);
        assertEquals(-5-(-3*(6-4)/(-7*9.0)), Double.parseDouble(testParser.ExpressionMathParser("-5-(-3*(6-4)/(-7*9))").evaluate().toString()), DELTA);
        assertEquals(769./.5+6.*.3-.7/8., Double.parseDouble(testParser.ExpressionMathParser("769./.5+6.*.3-.7/8.").evaluate().toString()), DELTA);
        assertEquals(139.0, Double.parseDouble(testParser.ExpressionMathParser("123-(((5-6)-7)-8)").evaluate().toString()), DELTA);
    }
}
