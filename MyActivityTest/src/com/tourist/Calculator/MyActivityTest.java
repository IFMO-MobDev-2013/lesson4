package com.tourist.Calculator;

import android.test.ActivityInstrumentationTestCase2;

public class MyActivityTest extends ActivityInstrumentationTestCase2<MyActivity> {

    public MyActivityTest() {
        super("com.tourist.Calculator", MyActivity.class);
    }

    public void test() throws Exception {
        assertEquals(ExpressionParser.parse("2*2").evaluate(), 4.0);
        assertEquals(ExpressionParser.parse("0").evaluate(), 0.0);
        assertEquals(ExpressionParser.parse("-(--(----25))").evaluate(), -25.0);
        assertEquals(ExpressionParser.parse("1/3*3").evaluate(), 1.0);
        assertEquals(ExpressionParser.parse("2+2*2").evaluate(), 6.0);
        assertEquals(ExpressionParser.parse("-5-(-3*(6-4)/(-7*9))").evaluate(), -5-(-3*(6-4)/(-7*9.0)));
        assertEquals(ExpressionParser.parse("769./.5+6.*.3-.7/8.").evaluate(), 769./.5+6.*.3-.7/8.);
        assertEquals(ExpressionParser.parse("123-(((5-6)-7)-8)").evaluate(), 139.0);
        assertEquals(ExpressionParser.parse("5.0E9-4999.0*1000000.").evaluate(), 1000000.0);
        assertEquals(ExpressionParser.parse("2.0E-123*2.1E123").evaluate(), 4.2);
        assertEquals(ExpressionParser.parse("123E+123").evaluate(), 123E+123);
        try { ExpressionParser.parse(")"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("("); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("()"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("5+"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("123/()"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("((987-654)+123"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("123E*123"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("(-()+5)"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("((234+24)-53+)"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("(+(344)+42.34.3)"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("1.."); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse(".."); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse(".2."); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("..3"); fail(); } catch (ParserException e) { }
        try { ExpressionParser.parse("132..465"); fail(); } catch (ParserException e) { }
    }

}
