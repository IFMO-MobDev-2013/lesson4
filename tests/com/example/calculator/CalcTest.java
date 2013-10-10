package com.example.calculator;


import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: elena
 * Date: 10.10.13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class CalcTest implements Test {
    @Before
    public void setUp() throws Exception {

    }

        public void check(String expression, double res) {
            double v = MyActivity.calculate(expression);

            assertEquals(res, v, 1e-5);
        }




    @Test
    public void testGetValue() throws Exception {
        check("1*-1", 1*-1);
        check("(1-(1+0)*6)/(234-2)*(1+8)", (double)(1-(1 + 0 )* 6) /(234-2)*(1+ 8 ));
        check("(1)/(2)*(3)", (double)(1)/(2)*(3));
        check("(((0*13.4)+923874)-123)/6", (((0*13.4)+923874)-123)/6);
        check("(((1.0)))+7.2", (((1.0)))+7.2);
        check("5.5*2*3*1*4*+0.5", 5.5*2*3*1*4*+0.5);
        check("1", 1);
    }


    @Override
    public Class<? extends Throwable> expected() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long timeout() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int hashCode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
