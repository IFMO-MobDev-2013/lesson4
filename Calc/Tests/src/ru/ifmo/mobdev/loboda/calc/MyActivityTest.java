package ru.ifmo.mobdev.loboda.calc;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;

public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {

    public MyActivityTest() {
        super("ru.ifmo.mobdev.loboda.calc", MyActivity.class);
    }

    public void priorityTest(){
        String[] tests = {"2+4*6/2+15*(-(-2+3*5)))",
                          "-2+5*(3)*12+12+12+1-32-(-(+(+(32))))",
                          "-56-56-56-56*23+1234-34",
                          "32-(23+52)*(-13)",
                          "((((((((((7))))))))))"};
        int[] answers = {2+4*6/2+15*(-(-2+3*5)), -2+5*(3)*12+12+12+1-32-(-(+(+(32)))), -56-56-56-56*23+1234-34, 32-(23+52)*(-13), ((((((((((7))))))))))};
        for(int i = 0; i < tests.length; ++i){
            String s = tests[i];
            String appAns = Parser.parse(s).toString();
            String ans = (new Integer(answers[i])).toString();
            Assert.assertEquals(ans, appAns);
        }
    }
    public void divisionByZero(){
        String s = "1/0";
        String appAns = Parser.parse(s).toString();
        Assert.assertEquals(appAns, "Infinity");
    }
    public void bigIntegers(){
        String s1 = "";
        String s2 = "10";
        for(int i = 0; i < 1000; ++i){
            s1 = s2.concat("7");
        }
        String appAns = Parser.parse(s1 + "*" + s2).toString();
        Assert.assertEquals(appAns, s1 + "0");
    }
}
