package com.example.AndroidHomework4;


import junit.framework.Assert;
import org.junit.Test;
import ru.ifmo.ctd.isaev.CalculatingException;
import ru.ifmo.ctd.isaev.Parser;
import ru.ifmo.ctd.isaev.ParsingException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 10.10.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class ParserTest extends Assert {
    private final Map<String, Double> tests = new HashMap<String, Double>();
    Parser parser = new Parser();

    public ParserTest() {
        super();
        tests.put("2*2",4.0);
        tests.put("(((((2*2)))))",4.0);
        tests.put("(2^2)+(2^2)",8.0);
        tests.put("(28)+(1)+(5)*(0.2)+(3*(2)*4)/(3*(4)*2)",31.0);
        tests.put("1/2", 0.5);
    }



    @Test
    public void test() throws ParsingException, CalculatingException {
          for(String s:tests.keySet()){
                  assertEquals(parser.parse(s).evaluate(null), tests.get(s), 0.00003);

          }
    }

}
