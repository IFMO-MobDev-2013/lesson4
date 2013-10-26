package com.blumonk.Calc;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import com.blumonk.Calc.Parser.CalcException;
import com.blumonk.Calc.Parser.ParseException;
import com.blumonk.Calc.Parser.Parser;
import junit.framework.Assert;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.blumonk.Calc.CalcActivityTest \
 * com.blumonk.Calc.tests/android.test.InstrumentationTestRunner
 */
public class CalcActivityTest extends ActivityInstrumentationTestCase2<CalcActivity> {

    public CalcActivityTest() {
        super("com.blumonk.Calc", CalcActivity.class);
    }

    public void simpleCases() {
        String[] cases = {"2+2*2", "1+(-1)*(+4)+7+8/5.0+9*123.45", "((((5+8+9))))", "4.4", "77*(6+5/1)-(-2)"};
        double[] answers = {2 + 2 * 2, 1 + (-1) * (+4) + 7 + 8 / 5.0 + 9 * 123.45, ((((5 + 8 + 9)))), 4.4, 77 * (6 + 5 / 1) - (-2)};
        for (int i = 0; i < 5; ++i) {
            try {
                double buff = Parser.parse(cases[i]).evaluate();
                Assert.assertEquals(Math.abs(answers[i] - buff) < 1e-6, true);
            } catch (Exception e) {
                Log.d("Error", "Error in parser", e);
            }
        }
    }

    public void extremalCases() {
        String[] cases = {"1/0", "((.-", "--431", "(*4+5+5)", "j"};
        try {
            double buff = Parser.parse(cases[0]).evaluate();
            Assert.fail("This should fail");
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), CalcException.class);
            Log.d("Error", "Error in parser", e);
        }
        for (int i = 1; i < 5; ++i) {
            try {
                double buff = Parser.parse(cases[i]).evaluate();
                Assert.fail("This should fail");
            } catch (Exception e) {
                Assert.assertEquals(e.getClass(), ParseException.class);
                Log.d("Error", "Error in parser", e);
            }
        }
    }

}
