package com.example.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;





public class ComputationTest {
    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testPlus() throws Exception {
        assertEquals(2+3,Computation.getRes("2+3"),0);
    }

    @Test
    public void testMinus() throws Exception {
        assertEquals(2-3,Computation.getRes("2-3"),0);
    }

    @Test
    public void testMult() throws Exception {
        assertEquals(2*3,Computation.getRes("2*3"),0);
    }

    @Test
    public void testDiv() throws Exception {
        assertEquals(4/2,Computation.getRes("4/2"),0);
    }

    @Test
    public void testHardlyExpression1() throws Exception {
        assertEquals((3+4*2)-(4/2)*(2*(3+5))*(14/7+2),Computation.getRes("(3+4*2)-(4/2)*(2*(3+5))*(14/7+2)"),0);
    }

    @Test
    public void testHardlyExpression2() throws Exception {
        assertEquals((((double)1-3)*(2-3)*(3-4)*(4-5)*(5-6)*(6-7))/((4/2)+(6/3)),Computation.getRes("((1-3)*(2-3)*(3-4)*(4-5)*(5-6)*(6-7))/((4/2)+(6/3))"),0);
    }

    @Test
    public void testHardlyExpression3() throws Exception {
        assertEquals((double)2+3*12-4*(2+3+5*4)*(12+3-4*0.5)/12*149-(16*2),Computation.getRes("2+3*12-4*(2+3+5*4)*(12+3-4*0.5)/12*149-(16*2)"),0);
    }
}
