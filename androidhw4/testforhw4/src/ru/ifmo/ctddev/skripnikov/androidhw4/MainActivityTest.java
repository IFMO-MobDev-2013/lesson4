package ru.ifmo.ctddev.skripnikov.androidhw4;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("ru.ifmo.ctddev.skripnikov.androidhw4", MainActivity.class);
    }

    public void test() throws EvaluateException, ParseException {
        mainTests();
        parseExceptionTest();
        evaluateExceptionTest();
    }

    public void mainTests() throws EvaluateException, ParseException {
        String[] tests = {
                "(--34+74*3/3+ -78 / 6)*(3*5)",
                "1",
                "54/1000000",
                "5.375E-8",
        };
        double[] answers = {
                1815.0,
                1.0,
                5.4E-5,
                5.375E-8
        };
        for (int i = 0; i < tests.length; i++)
            Assert.assertEquals(answers[i], Parser.parse(tests[i]).evaluate());
    }

    public void parseExceptionTest() {
        try {
            Parser.parse("((--34+74*");
            Assert.fail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void evaluateExceptionTest() {
        try {
            Parser.parse("1/0").evaluate();
            Assert.fail();
        } catch (EvaluateException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            Assert.fail();
        }
    }
}