package ru.marsermd.Swipylator;

import junit.framework.TestCase;
import org.junit.Test;
import ru.marsermd.Swipylator.core.ExpressionParserBuilder;
import ru.marsermd.Swipylator.core.Operand;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: marsermd
 * Date: 16.10.13
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionParserBuilderTest extends TestCase{


    String expression = "(789000000000000000000 * π+e % 9) * (55 - 3  /6)";
    private Map<String, Double> doubleValues;
    private Map<String, BigInteger> bigIntValues;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        doubleValues = new HashMap();
        doubleValues.put("π", Math.PI);
        doubleValues.put("e", Math.E);
        bigIntValues = new HashMap();
        bigIntValues.put("π", BigInteger.valueOf((int) Math.PI));
        bigIntValues.put("e", BigInteger.valueOf((int)Math.E));
    }

    @Test
    public void testBuildForDouble() throws Exception {
        Operand<Double> answer =
                ExpressionParserBuilder.buildForDouble().build(expression);
        assertEquals("failed testing for doubles", "1.350900549006879E23", answer.evaluate(doubleValues).toString());
    }

    @Test
    public void testBuildForBigInteger() throws Exception {
        Operand<BigInteger> answer =
                ExpressionParserBuilder.buildForBigInteger().build(expression);
        assertEquals("failed testing for bigIntegers", "130185000000000000000110", answer.evaluate(bigIntValues).toString());
    }
}
