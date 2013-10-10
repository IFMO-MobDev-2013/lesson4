package com.example.lesson4;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: Ruslan
 * Date: 10.10.13
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public class ParserTest {
    Parser parser;

    @Before
    public void setUp(){
        parser = new Parser();
    }

    @Test
    public void testNumber() {
        assertEquals(1, parser.parse("1"), 0);
    }

    @Test
    public  void testSum() {
        assertEquals(2, parser.parse("1 + 1"), 0);
    }

    @Test
    public void testSubtract(){
        assertEquals(3, parser.parse("4 - 1"), 0);
    }

    @Test
    public void testMultiply(){
        assertEquals(4, parser.parse("2 * 2"), 0);
    }

    @Test
    public void testDivision(){
        assertEquals(5, parser.parse("25 / 5"), 0);
    }

    @Test
    public void testModule(){
        assertEquals(6, parser.parse("26 % 20"), 0);
    }

    @Test
    public void testBigInput(){
        assertEquals(2 * 3.0 / ((5 + 3) - 1) + 239 % 400, parser.parse("2 * 3.0 / ((5 + 3) - 1) + 239 % 400"), 0);
    }

}
