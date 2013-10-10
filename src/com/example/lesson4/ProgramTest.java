package com.example.lesson4;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;

import java.util.HashMap;

public class ProgramTest extends ActivityInstrumentationTestCase2<Program>
{
    public ProgramTest()
    {
        super("com.example.lesson4", Program.class);
    }

    public void test1() throws Exception
    {
        ExpressionsParser p = new ExpressionsParser();
        Evaluator e = p.parse("1+2*log(2,8)");
        Evaluator e2 = p.parse("36/9+(-23)");
        Evaluator e3 = p.parse("ln(16)/ln(2)+(+(+23))");
        Assert.assertEquals(e.evaluate(new HashMap<String, Double>()), 7);
        Assert.assertEquals(e2.evaluate(new HashMap<String, Double>()), -19);
        Assert.assertEquals(e3.evaluate(new HashMap<String, Double>()), 27);
    }

    public void test2() throws Exception
    {
        ExpressionsParser p = new ExpressionsParser();
        Evaluator e = p.parse("5^2+(21-(20-1)");
        Assert.assertEquals(e.evaluate(new HashMap<String, Double>()), 27);
    }

    public void test3() throws Exception
    {
        ExpressionsParser p = new ExpressionsParser();
        Evaluator e = p.parse("sqrt(16)^2");
        Assert.assertEquals(e.evaluate(new HashMap<String, Double>()), 16);
    }
}
