
package com.example.h4_calculator;

/**
 * Created with IntelliJ IDEA.
 * User: carbo_000
 * Date: 10.10.13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;

import static com.example.h4_calculator.PollandParcer.evaluate;
import static com.example.h4_calculator.PollandParcer.makeSpaces;
import static com.example.h4_calculator.PollandParcer.parse;

public class TestingParcer extends TestCase{

    public TestingParcer(String testName) {
        super(testName);
    }

    public static void testExpression(String expression,double result)
    {
        assertTrue(Double.parseDouble(evaluate(parse(makeSpaces(expression)))) == result);
    }

    public static void main()
    {
        testExpression("((1 + 3) * 6.3) ^ 5", 10162550.20032);
    }
}
