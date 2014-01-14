package com.example.Calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchParserTest {
    String[] e = {"2+2", "4-2", "10*10", "2/4", "3++3", "2--2", "(2+(3--3))/4*10"};
    MatchParser a = null;
    @Before
    public void setUp() throws Exception {
         a = new MatchParser();
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(a.parse(e[0]), 4, 0);
        assertEquals(a.parse(e[1]), 2, 0);
        assertEquals(a.parse(e[2]), 100, 0);
        assertEquals(a.parse(e[3]), 0.5, 0);
        assertEquals(a.parse(e[4]), 6, 0);
        assertEquals(a.parse(e[5]), 4, 0);
        assertEquals(a.parse(e[6]), 20, 0);
    }
}
