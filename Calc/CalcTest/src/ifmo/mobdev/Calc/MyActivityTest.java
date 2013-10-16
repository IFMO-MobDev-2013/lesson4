package ifmo.mobdev.Calc;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;

public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {

    public MyActivityTest() {
        super(MyActivity.class);
    }

    public void testCalc() throws Exception {
        Parser parser = new Parser();
        Assert.assertEquals(6.0, parser.parseExpression("1 * 2 * 3").evaluate());
        Assert.assertEquals(3.0, parser.parseExpression("1 + - + - + 2").evaluate());
        Assert.assertEquals(15.0, parser.parseExpression("3 + ( 4 - 2 + 5 - 3 ) * 3").evaluate());
        Assert.assertEquals(6.0, parser.parseExpression("45 / 5 + 3 - ( 3 * 2 - 3 ) * 2").evaluate());
        Assert.assertEquals(22.675, parser.parseExpression("45.35 / 2").evaluate());
        Assert.assertEquals(37.8, parser.parseExpression("3.2 * 4 + 25").evaluate());
        Assert.assertEquals(998001.0, parser.parseExpression("999 * 999").evaluate());
        Assert.assertEquals(18.0, parser.parseExpression("1 + - + - 3 + - 3 * 5.2 + - 4 - + 0.4 + + + 34").evaluate());
        Assert.assertEquals(25.0, parser.parseExpression(" ( 3 - 2 * 2 ) * 3 - ( 5 - 3 + 2 ) * ( 6 - 3 + 3 + - 13)").evaluate());
        Assert.assertEquals(23256908805.0, parser.parseExpression("535454 * 43434 - 435 + 438 - 234").evaluate());

        try {
            Assert.assertEquals(0.0, parser.parseExpression("1.45.34 + 3"));
            fail();
        } catch (FunctionException e) {
            Assert.assertEquals(e.getMessage(), "incorrect double: 1.45.34 ");
        }

        try {
            Assert.assertEquals(0.0, parser.parseExpression(" + - + - "));
            fail();
        } catch (FunctionException e) {
            Assert.assertEquals("empty string", e.getMessage());
        }

        try {
            Assert.assertEquals(0.0, parser.parseExpression(" 3 + - 13 + - "));
            fail();
        } catch (FunctionException e) {
            Assert.assertEquals("empty string", e.getMessage());
        }

        try {
            Assert.assertEquals(0.0, parser.parseExpression(" 35 + 4 * ( 3 - 2"));
            fail();
        } catch (FunctionException e) {
            Assert.assertEquals("not enough brackets", e.getMessage());
        }

        try {
            Assert.assertEquals(0.0, parser.parseExpression(" 35 + 4 * ) ( "));
            fail();
        } catch (FunctionException e) {
            Assert.assertEquals("wrong operands order", e.getMessage());
        }

        try {
            Assert.assertEquals(0.0, parser.parseExpression(" 35 +  * 4"));
            fail();
        } catch (FunctionException e) {
            Assert.assertEquals("wrong operands order", e.getMessage());
        }
    }
}
