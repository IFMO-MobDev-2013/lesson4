package com.example.AndroidHomework4;


import junit.framework.Assert;
import org.junit.Test;
import ru.ifmo.ctd.isaev.CalculatingException;
import ru.ifmo.ctd.isaev.Evaluable;
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
        tests.put("2*-2",-4.0);
        tests.put("2/3*3+((5))",7.0);
        tests.put(" 2  * ( 2  +  9 * 1  /(3-  9))",1.0);
        tests.put("78*33-18/(15^6+12^13)/(33+9*0.3)",2574.0);
        tests.put("(5.3-(0.7^6-3)*(5-45+6/7-8)^(13-33))",5.3);
    //   tests.put("7-8/(33+(67-21+9)*(5-17^4)*5+6/7-8)^(13-33)",-1.33391389641E+148);
        tests.put("(8-46*44)/(33^4-(55+5/8^43-11)-15/88/34^11)",-0.00170000767365	);

    }



    @Test
    public void test() {
          for(String s:tests.keySet()){
            try {
                Evaluable e =  parser.parse(s);
                double d  =e.evaluate(null);
                assertEquals(d, tests.get(s), 0.00000000001);
            } catch (ParsingException e) {
                 System.out.println("lol "+s);
                e.printStackTrace();//To change body of catch statement use File | Settings | File Templates.
            } catch (CalculatingException e) {
                System.out.println("lol1 "+s);//ody of catch statement use File | Settings | File Templates.
            }

          }
    }

}
