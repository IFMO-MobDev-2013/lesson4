package ru.skipor.androidLecture4.Calculator;

import org.junit.Before;
import org.junit.Test;


import java.lang.Double;

import static org.junit.Assert.*;

/**
 * Created by vladimirskipor on 10/10/13.
 */
public class CalculatorTest {

    private Double eps = 0.00000001;
    Calculator calculator;
    String formula1 = "2*2.5-3";
    String someConst = "123";
    String someDigit = "5";


    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void testAddBracket() throws Exception {
        calculator.setValidText(formula1);
        assertTrue((calculator.addBracket().equals(formula1)));

        calculator.setValidText(formula1 + someConst);
        assertTrue(calculator.addBracket().equals(formula1 + someConst + ")"));

    }

    @Test
    public void testAddOperation() throws Exception {
        calculator.setValidText(formula1);
        assertTrue((calculator.addOperation("*").equals(formula1 + "*")));
    }

    @Test
    public void testAddDigit() throws Exception {
        calculator.setValidText(formula1);
        assertTrue((calculator.addDigit(someDigit).equals(formula1 + someDigit)));

    }

    @Test
    public void testAddDot() throws Exception {
        calculator.setValidText(formula1);
        assertTrue((calculator.addDot().equals(formula1 + ".")));

    }

    @Test
    public void testClear() throws Exception {
        calculator.setValidText(formula1);
        assertTrue((calculator.clear().equals(formula1.substring(0, formula1.length() - 1))));

    }

    @Test
    public void testClearAll() throws Exception {
        calculator.setValidText(formula1);
        assertTrue((calculator.clearAll().equals("")));


    }

    @Test
    public void testEvaluate() throws Exception {
        calculator.setValidText(formula1);
        assertTrue(Math.abs(Double.valueOf(calculator.evaluate()) - 5) < eps);

    }

    @Test
    public void testErrors() throws Exception {
        calculator.setValidText("1/0");
        assertTrue(calculator.evaluate().equals(calculator.errorMessage));

    }
}
