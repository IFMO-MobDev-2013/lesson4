package com.example.lesson4;

import junit.framework.TestCase;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * User: Genyaz
 * Date: 10.10.13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public class MyTestCase extends TestCase {
    @Test
    public void testExpressionParser() {
        double eps = 1e-9;
        double expected, got;
        got = ExpressionParser.parseExpression("1+2+3-4");
        expected = 2;
        assertTrue(Math.abs(got - expected) < eps);
        got = ExpressionParser.parseExpression("1*2*3*4");
        expected = 24;
        assertTrue(Math.abs(got - expected) < eps);
        got = ExpressionParser.parseExpression("1+2*3/-3");
        expected = -1;
        assertTrue(Math.abs(got - expected) < eps);
        got = ExpressionParser.parseExpression("(1+2)/-1");
        expected = -3;
        assertTrue(Math.abs(got - expected) < eps);
        got = ExpressionParser.parseExpression("(1*(1*(1*(1*(-1)/-1)/-1)/-1)/-1)");
        expected = -1;
        assertTrue(Math.abs(got - expected) < eps);
        got = ExpressionParser.parseExpression("(11*11)+2*(3/-5)");
        expected = 119.8;
        assertTrue(Math.abs(got - expected) < eps);
    }
}
