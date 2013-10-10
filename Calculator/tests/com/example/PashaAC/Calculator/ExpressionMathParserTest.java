package com.example.PashaAC.Calculator;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExpressionMathParserTest extends TestCase {
    private ExpressionMathParser testParser;

    @Before
    public void setUp() throws Exception {
        testParser = new ExpressionMathParser();
    }

    @Test
    public void testExpressionMathParser() throws Exception {
        assertEquals(5, Double.parseDouble(testParser.ExpressionMathParser("5").evaluate().toString()), 0);
        assertEquals(2+3*12-4*(2+3+5.0/4)*(12+3-4*0.5)/12.0*149-(16*2), Double.parseDouble(testParser.ExpressionMathParser("2+3*12-4*(2+3+5/4)*(12+3-4*0.5)/12*149-(16*2)").evaluate().toString()), 0);
        assertEquals(2+3*12-4*(2+3+5.0/4)*(12+3-4*0.5), Double.parseDouble(testParser.ExpressionMathParser("2+3*12-4*(2+3+5/4)*(12+3-4*0.5)").evaluate().toString()), 0);
        assertEquals(2+3*12-4*(2+3+5.0/4), Double.parseDouble(testParser.ExpressionMathParser("2+3*12-4*(2+3+5/4)").evaluate().toString()), 0);
        assertEquals(-4*(2+3+5.0/4), Double.parseDouble(testParser.ExpressionMathParser("-4*(2+3+5/4)").evaluate().toString()), 0);
        assertEquals(2+3, Double.parseDouble(testParser.ExpressionMathParser("2+3").evaluate().toString()), 0);
        assertEquals(5, Double.parseDouble(testParser.ExpressionMathParser("2--3").evaluate().toString()), 0);
        assertEquals(5/(-1), Double.parseDouble(testParser.ExpressionMathParser("5/(-1)").evaluate().toString()), 0);
        assertEquals((+5.3-+4)/(-1), Double.parseDouble(testParser.ExpressionMathParser("(+5.3-+4)/(-1)").evaluate().toString()), 0.0001);
        assertEquals(-3-(2-3*(4.3/1.123)), Double.parseDouble(testParser.ExpressionMathParser("-3-(2-3*(4.3/1.123))").evaluate().toString()), 0);
        assertEquals((((-3-(2-3-(2+3))))), Double.parseDouble(testParser.ExpressionMathParser("(((-3-(2-3-(2+3)))))").evaluate().toString()), 0);
        assertEquals((((5)))/(-1), Double.parseDouble(testParser.ExpressionMathParser("(((5)))/(-1)").evaluate().toString()), 0);
        assertEquals(10, Double.parseDouble(testParser.ExpressionMathParser("2----+--3+--+--5").evaluate().toString()), 0);
        assertEquals(7*8-23/23+3*0.3, Double.parseDouble(testParser.ExpressionMathParser("7*8-23/23+3*0.3").evaluate().toString()), 0);
        assertEquals(23.123/65.123781, Double.parseDouble(testParser.ExpressionMathParser("23.123/65.123781").evaluate().toString()), 0.0000001);
   }
}
