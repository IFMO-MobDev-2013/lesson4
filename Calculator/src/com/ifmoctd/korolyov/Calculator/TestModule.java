package com.ifmoctd.korolyov.Calculator;

import junit.framework.TestCase;

public class TestModule extends TestCase {

    private int testCount = 0;


    @Override


    protected void setUp() throws Exception {
        super.setUp();

    }


    private void checkTest(String countString, double result) {
        testCount++;
        String calc = "";
        try {
            calc = Double.toString(MyActivity.evaluate(MyActivity.normalizeString(countString)));

        } catch (Exception ex) {
            assertTrue(false);

        }

        double k = Math.abs(Double.parseDouble("" + calc) - result);
        assertTrue("Test #" + testCount + " failed", k < 1e-5);

    }


    public void runTest() {
        checkTest("1*1*1.1", 1 * 1 * 1.1);
        checkTest("-1/(-1)", -1 / (-1));
        checkTest("1*2*3*4*5", 1 * 2 * 3 * 4 * 5);
        checkTest("1*2*3*4*5/10", 1 * 2 * 3 * 4 * 5 / 10);
        checkTest("(2+2)*2", (2 + 2) * 2);
        checkTest("2+2*2", 2 + 2 * 2);
        checkTest("1/2/3+3", 1.0 / 2.0 / 3.0 + 3);
        checkTest("1/2/(3+3)", 1.0 / 2.0 / (3.0 + 3.0));


    }
}

