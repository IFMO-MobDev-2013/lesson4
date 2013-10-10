package ru.nechaev.calc;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ViruZ
 * Date: 10.10.13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public class CalcActivTest {
    @Before
    private CalcActiv calcActiv;
    public void setUp() throws Exception {
        calcActiv = new CalcActiv();

    }

    @Test
    public void testOnCreate() throws Exception {
        assert.equals("13", calcActiv.MathPars.Parse("13"));

    }
}
