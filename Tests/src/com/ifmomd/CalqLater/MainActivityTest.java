package com.ifmomd.CalqLater;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;

import java.util.HashMap;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.ifmomd.CalqLater.MainActivityTest \
 * com.ifmomd.CalqLater.tests/android.test.InstrumentationTestRunner
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("com.ifmomd.CalqLater", MainActivity.class);
    }

    public void testOperations() throws Exception {
        String[] tests = {"32*64*128*256*1024*2048/3.14",
                "-(-(-(3)))",
                "30+4*100+7*1000+5*10000+2*1000000+1*10000000+3*100000000",
                "32-(23.23+52)*(-1.53)",
                "(-((((((((-(-7))))))))))"};
        double[] answers = {32.0*64*128*256*1024*2048/3.14, -(-(-(3))), 312057430, 32 - (23.23 + 52) * (-1.53), (-((((((((-(-7))))))))))};
        for (int i = 0; i < tests.length; ++i) {
            String s = tests[i];
            Evaluable<Double> e = ExpressionsParser.parseExpression(s,
                                                                    Operations.DoubleOperations.ADD,
                                                                    Operations.DoubleOperations.SUBTRACT,
                                                                    Operations.DoubleOperations.MULTIPLY,
                                                                    Operations.DoubleOperations.DIVIDE,
                                                                    Operations.DoubleOperations.MODULO, new DoubleNumberFactory());
            String ans = (new Double(answers[i])).toString();
            Double res = e.evaluate(new HashMap<String, Double>());
            Assert.assertEquals(ans, res.toString());
        }
    }

    public void testNaNs() throws Exception {
        Evaluable<Double> e = ExpressionsParser.parseExpression("1/0",
                                                                Operations.DoubleOperations.ADD,
                                                                Operations.DoubleOperations.SUBTRACT,
                                                                Operations.DoubleOperations.MULTIPLY,
                                                                Operations.DoubleOperations.DIVIDE,
                                                                Operations.DoubleOperations.MODULO, new DoubleNumberFactory());
        Assert.assertTrue(e.evaluate(new HashMap()).isInfinite());
        e = ExpressionsParser.parseExpression("0/0",
                                                                Operations.DoubleOperations.ADD,
                                                                Operations.DoubleOperations.SUBTRACT,
                                                                Operations.DoubleOperations.MULTIPLY,
                                                                Operations.DoubleOperations.DIVIDE,
                                                                Operations.DoubleOperations.MODULO, new DoubleNumberFactory());
        Assert.assertTrue(e.evaluate(new HashMap()).isNaN());
    }
}
