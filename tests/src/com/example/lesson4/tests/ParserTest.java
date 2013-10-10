package com.example.lesson4.tests;

import com.example.lesson4.Parser;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: satori
 * Date: 10/8/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParserTest extends TestCase {
    public void testTokens() throws Exception {
        String s = "(2+34.5)+4+5/6-1+1.3*(+1*-1)";
        String[] tok = {"(","2","+","34.5",")","+","4","+","5","/","6","-","1","+","1.3","*","(","+","1","*","-","1",")"};
        double d = Parser.parse(s);
        for (int i = 0; i < Parser.tokens.length; i++) {
            assertEquals(Parser.tokens[i], tok[i]);
        }


    }
    public void testTokensWithSpaces() throws Exception {
        String s = "(2+ 34.5)+4 +5 /6-1+ 1.3 *  (+1* - 1)";
        String[] tok = {"(","2","+","34.5",")","+","4","+","5","/","6","-","1","+","1.3","*","(","+","1","*","-","1",")"};
        double d = Parser.parse(s);
        for (int i = 0; i < Parser.tokens.length; i++) {
            assertEquals(Parser.tokens[i], tok[i]);
        }


    }
    public void testParsing() throws Exception {
        String s = "3*10/5";
        assertEquals(Parser.parse(s), 6.0);
    }

    public void testParsing1() throws Exception {
        String s = "(-600/200) * -2";
        assertEquals(Parser.parse(s), 6.0);
    }

    public void testParsing2() throws Exception {
        String s = "--100*10 / -1 * ++0.5";
        assertEquals(Parser.parse(s), -500.0);
    }

    public void testParsing3() throws Exception {
        String s = "-(--100/--(25/+-5))";
        assertEquals(Parser.parse(s), 20.0);
    }

    public void testParsing4() throws Exception {
        String s = "- 100 / -200 * 100 / 100";
        assertEquals(Parser.parse(s), 0.5);

    }

    public void testParsing5() throws Exception {
        String s = "34.5 + 34.5 -- 40.0 * - 10";
        assertEquals(Parser.parse(s), -331.0);

    }

    public void testParsing6() throws Exception {
        String s = "(--3 * (- 11 - 1 * 100)/ 1)";
        assertEquals(Parser.parse(s), -333.0);

    }

    public void testParsing7() throws Exception {
        String s = "(-100)*(0)*(-2)";
        assertEquals(Parser.parse(s), 0.0);

    }

    public void testParsing8() throws Exception {
        String s = "(-100)/(-2)/(-5)";
        assertEquals(Parser.parse(s), -10.0);
        ;

    }
}
