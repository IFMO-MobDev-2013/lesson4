package ru.ifmo.ctddev.koval.calculator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ru.ifmo.ctddev.koval.calculator.CalculatorActivityTest \
 * ru.ifmo.ctddev.koval.calculator.tests/android.test.InstrumentationTestRunner
 */
public class CalculatorActivityTest extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    public void testCListener() throws Exception {
        setInput("abracadabra");
        clickButton(R.id.c);
        Thread.sleep(1000);
        assertEquals("", getInput());
    }

    public void testOneListener() throws Exception {
        setInput("");
        clickButton(R.id.one);
        Thread.sleep(1000);
        assertEquals("1", getInput());
    }

    public void testTwoListener() throws Exception {
        setInput("");
        clickButton(R.id.two);
        Thread.sleep(1000);
        assertEquals("2", getInput());
    }

    public void testThreeListener() throws Exception {
        setInput("");
        clickButton(R.id.three);
        Thread.sleep(1000);
        assertEquals("3", getInput());
    }

    public void testFourListener() throws Exception {
        setInput("");
        clickButton(R.id.four);
        Thread.sleep(1000);
        assertEquals("4", getInput());
    }

    public void testFiveListener() throws Exception {
        setInput("");
        clickButton(R.id.five);
        Thread.sleep(1000);
        assertEquals("5", getInput());
    }

    public void testSixListener() throws Exception {
        setInput("");
        clickButton(R.id.six);
        Thread.sleep(1000);
        assertEquals("6", getInput());
    }

    public void testSevenListener() throws Exception {
        setInput("");
        clickButton(R.id.seven);
        Thread.sleep(1000);
        assertEquals("7", getInput());
    }

    public void testEightListener() throws Exception {
        setInput("");
        clickButton(R.id.eight);
        Thread.sleep(1000);
        assertEquals("8", getInput());
    }

    public void testNineListener() throws Exception {
        setInput("");
        clickButton(R.id.nine);
        Thread.sleep(1000);
        assertEquals("9", getInput());
    }

    public void testZeroListener() throws Exception {
        setInput("");
        clickButton(R.id.zero);
        Thread.sleep(1000);
        assertEquals("0", getInput());
    }

    public void testPlusListener() throws Exception {
        setInput("");
        clickButton(R.id.plus);
        Thread.sleep(1000);
        assertEquals("+", getInput());
    }

    public void testSubstractListener() throws Exception {
        setInput("");
        clickButton(R.id.substract);
        Thread.sleep(1000);
        assertEquals("-", getInput());
    }

    public void testDivisionListener() throws Exception {
        setInput("");
        clickButton(R.id.division);
        Thread.sleep(1000);
        assertEquals("/", getInput());
    }

    public void testTimesListener() throws Exception {
        setInput("");
        clickButton(R.id.times);
        Thread.sleep(1000);
        assertEquals("*", getInput());
    }

    public void testOpenBracketListener() throws Exception {
        setInput("");
        clickButton(R.id.openBracket);
        Thread.sleep(1000);
        assertEquals("(", getInput());
    }

    public void testCloseBracketListener() throws Exception {
        setInput("");
        clickButton(R.id.closeBracket);
        Thread.sleep(1000);
        assertEquals(")", getInput());
    }

    public void testPointListener() throws Exception {
        setInput("");
        clickButton(R.id.point);
        Thread.sleep(1000);
        assertEquals(".", getInput());
    }

    public void testBackspaceListener() throws Exception {
        setInput("abcde");
        clickButton(R.id.backspace);
        Thread.sleep(1000);
        assertEquals("abcd", getInput());
    }

    public void testCountListener() throws Exception {
        setInput("2 * 2 + 2");
        clickButton(R.id.count);
        Thread.sleep(1000);
        assertEquals("6", getResult());

        setInput("1 + 2 + 3 + 4 + 5");
        clickButton(R.id.count);
        Thread.sleep(1000);
        assertEquals("15", getResult());

        setInput("smth wrong");
        clickButton(R.id.count);
        Thread.sleep(1000);
        assertEquals("Invalid expression", getResult());
    }

    private String getResult() {
        return ((TextView) getActivity().findViewById(R.id.result)).getText().toString();
    }

    private String getInput() {
        return ((EditText) getActivity().findViewById(R.id.input)).getText().toString();
    }

    private void setInput(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((EditText) getActivity().findViewById(R.id.input)).setText(text);
            }
        });
    }

    private void clickButton(int id) {
        final Button button = (Button) getActivity().findViewById(id);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
    }

    public CalculatorActivityTest() {
        super("ru.ifmo.ctddev.koval.calculator", CalculatorActivity.class);
    }

}
