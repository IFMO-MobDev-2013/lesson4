package ru.georgeee.android.gcalc;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import ru.georgeee.android.gcalc.calc.Evaluator;
import ru.georgeee.android.gcalc.calc.number.GBigInt;
import ru.georgeee.android.gcalc.calc.number.GDouble;
import ru.georgeee.android.gcalc.calc.number.GLong;

import java.math.BigInteger;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ru.georgeee.android.gcalc.MainActivityTest \
 * ru.georgeee.android.gcalc.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("ru.georgeee.android.gcalc", MainActivity.class);
    }

    public void ted(String source, double checkValue) {
        GDouble result = Evaluator.evaluateDouble(source);
        Log.d(MainActivityTest.class.getName(), "Expression: {" + source + "} value=" + result);
        assertTrue((Double.isNaN(result.getValue()) && Double.isNaN(checkValue)) || Math.abs(result.getValue() - checkValue) < 1e-9);
    }

    public void tei(String source, long checkValue) {
        GLong result = Evaluator.evaluateLong(source);
        Log.d(MainActivityTest.class.getName(), "Expression: {" + source + "} value=" + result);
        assertEquals(result.getValue(), checkValue);
    }

    public void tebi(String source, BigInteger checkValue) {
        GBigInt result = Evaluator.evaluateBigInt(source);
        Log.d(MainActivityTest.class.getName(), "Expression: {" + source + "} value=" + result);
        assertTrue(result.getValue().equals(checkValue));
    }

    public void testBasicSyntax() throws Exception {
        ted("23+32", 55);
        ted("2+(23+32)/5", 13);
        ted("acos(-1)", Math.PI);
        ted("pi", Math.PI);
        ted("5eexp2", 5 * Math.exp(3));
        ted("ln(exp2)", 2);
        tei("1<<2", 1 << 2);
        tei("3!!", 720);
        ted("-(log2(2^20)-2*3^3)/2", 17);
    }

    public void testIntegerOperations() throws Exception {
        tei("+3-200/3*8 XOR 20 + ~ 100 & 23 | 32 << 6 >> 20 %  14 * 2 ^ 3 !", +3 - 200 / 3 * 8 ^ 20 + ~100 & 23 | 32 << 6 >> 20 % 14 * 64);
    }

    public void testBigIntegerOperations() throws Exception {
        tebi("+3-200/3*8 XOR 20 + ~ 100 & 23 | 32 << 6 >> 20 %  14 * 2 ^ 3 !", BigInteger.valueOf(+3 - 200 / 3 * 8 ^ 20 + ~100 & 23 | 32 << 6 >> 20 % 14 * 64));
        tebi("1<<120", BigInteger.ONE.shiftLeft(120));
    }

    public void testDoubleOperations() throws Exception {
        ted("epiπ", Math.E * Math.PI * Math.PI);
        ted("√exp(log2(ln(lg(5456400))))", Math.sqrt(Math.exp(Math.log(Math.log(Math.log10(5456400))) / Math.log(2))));

        ted("sin(0.3)", Math.sin(0.3));
        ted("cos(0.3)", Math.cos(0.3));
        ted("tan(0.3)", Math.tan(0.3));
        ted("cot(0.3)", 1 / Math.tan(0.3));

        ted("asin(0.3)", Math.asin(0.3));
        ted("acos(0.3)", Math.acos(0.3));
        ted("atan(0.3)", Math.atan(0.3));
        ted("acot(0.3)", Math.PI / 2 - Math.atan(0.3));

        ted("sinh(0.3)", Math.sinh(0.3));
        ted("cosh(0.3)", Math.cosh(0.3));
        ted("tanh(0.3)", Math.tanh(0.3));
        ted("coth(0.3)", 1 / Math.tanh(0.3));

        ted("asinh(" + (Math.sinh(0.3)) + ")", 0.3);
        ted("acosh(" + (Math.cosh(0.3)) + ")", 0.3);
        ted("atanh(" + (Math.tanh(0.3)) + ")", 0.3);
        ted("acoth(" + (1 / Math.tanh(0.3)) + ")", 0.3);

        ted("-1+2*8/5^2-3", -1d + 2d * 8d / 25d - 3d);
    }
}
