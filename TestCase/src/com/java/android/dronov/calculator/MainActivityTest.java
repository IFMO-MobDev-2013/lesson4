package com.java.android.dronov.calculator;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.java.android.dronov.calculator.MainActivityTest \
 * com.java.android.dronov.calculator.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public static final String DIVISION_BY_ZERO="=Деление на ноль";
    public static final String PARSER_ERROR = "=Некорректный формат ввода выражения";
    Double resultTest;
    public MainActivityTest() {
        super("com.java.android.dronov.calculator", MainActivity.class);
    }

    ParseExpression parseExpression;
    public void testCorrectOperations() {
        parseExpression = new ParseExpression("(5+3)-2*4*(12/13)*21-(213)");
        resultTest = (5.0+3.0)-2.0*4.0*(12.0/13.0)*21.0-(213.0);
        assertEquals(parseExpression.getResult(), resultTest.toString());

        parseExpression = new ParseExpression("(0.1*12/123+(-(21)*(-32))+32*(12)-124124/3123)");
        resultTest = (0.1*12.0/123.0+(-(21)*(-32))+32*(12)-124124.0/3123.0);
        assertEquals(parseExpression.getResult(), resultTest.toString());

        parseExpression = new ParseExpression("-2*(-3+4.0)-0.*12.12-12.0/15.0+(-12)");
        resultTest = -2*(-3+4.0)-0.*12.12-12.0/15.0+(-12);
        assertEquals(parseExpression.getResult(), resultTest.toString());

        parseExpression = new ParseExpression("0.000001*0.000002-0.11123112");
        resultTest = 0.000001*0.000002-0.11123112;
        assertEquals(parseExpression.getResult(), resultTest.toString());

        parseExpression = new ParseExpression("(1-1+2-3+4)*(-1-2-3-4)*(31*3.12/31.23+(-(-(-42+32))))");
        resultTest = (1-1+2-3+4)*(-1-2-3-4)*(31*3.12/31.23+(-(-(-42+32))));
        assertEquals(parseExpression.getResult(), resultTest.toString());

        parseExpression = new ParseExpression("12/12/12/12/12/12/12/12");
        resultTest = 12./12./12./12./12./12./12./12.;
        assertEquals(parseExpression.getResult(), resultTest.toString());

        parseExpression = new ParseExpression("5-(-(-(-(-(+5)+5)+5)+5-(-(-(-(5))))))");
        resultTest = 5.0-(-(-(-(-(+5)+5)+5)+5-(-(-(-(5))))));
        assertEquals(parseExpression.getResult(), resultTest.toString());

    }

    public void testIncorrectOperations() {
        parseExpression = new ParseExpression("2*2/0");
        assertEquals(parseExpression.getResult(), DIVISION_BY_ZERO);

        parseExpression = new ParseExpression("4+(-3/)-42.12");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("(*4-3+1)");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("(4-1*32)+32-12*3)");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("(32*32-32+(-43+(-64))/(32*2-64+0.))");
        assertEquals(parseExpression.getResult(), DIVISION_BY_ZERO);

        parseExpression = new ParseExpression(")2+2-3/31*32+32-32(");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("()");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("(20-2.2+31.)/(20-20+)");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("5+");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("2---3");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("1+-2-2ad");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

        parseExpression = new ParseExpression("12/12/12/12/12/12/12-");
        assertEquals(parseExpression.getResult(), PARSER_ERROR);

    }

}
