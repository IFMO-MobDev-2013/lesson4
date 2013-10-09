import com.polarnick.polaris.math.expressionParser.ExpressionParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class ExpressionParserTest {

    private static final double DELTA = 0.0;

    @Test
    public void testIntegerValue() {
        checkExpression("239", 239);
    }

    @Test
    public void testDoubleValue() {
        checkExpression("239.017", 239.017);
    }

    @Test
    public void testUnaryPlus() {
        checkExpression("+2", 2);
    }

    @Test
    public void testUnaryMinus() {
        checkExpression("-239.017", -239.017);
    }

    @Test
    public void testPlus() {
        checkExpression("3+6", 9);
    }

    @Test
    public void testMinus() {
        checkExpression("10-13", -3);
    }

    @Test
    public void testComplexPlusAndMinus() {
        checkExpression("2+6-9+5-30", -26);
    }

    @Test
    public void testMultiply() {
        checkExpression("3*6", 18);
    }

    @Test
    public void testDivide() {
        checkExpression("4/3", 4.0 / 3.0);
    }

    @Test
    public void testComplexMultiplyAndDivide() {
        checkExpression("93*5/2*7/2", 93.0 * 5.0 / 2.0 * 7.0 / 2.0);
    }

    @Test
    public void testComplexExpression() {
        checkExpression("3*5-5/2+1-(-1.0)", 3 * 5 - 5.0 / 2.0 + 1 - (-1.0));
    }

    @Test
    public void testSuperComplexExpression() {
        checkExpression("-2+36*(4+2-9*5)-(-3)+(+2-4*3-6*(-4))-(12+5.9*1.2/(5.6+4.4*1.0))",
                -2 + 36 * (4 + 2 - 9 * 5) - (-3) + (+2 - 4 * 3 - 6 * (-4)) - (12 + 5.9 * 1.2 / (5.6 + 4.4 * 1.0)));
    }

    @Test
    public void testSuperPuperComplexExpression() {
        checkExpression("(((-1-(-(-(5.1*1.0)))+2.6*4-(5.1*123.0/54+1-123*2+9.0/(-(-(5.1*1.0)))))))",
                (((-1 - (-(-(5.1 * 1.0))) + 2.6 * 4 - (5.1 * 123.0 / 54 + 1 - 123 * 2 + 9.0 / (-(-(5.1 * 1.0))))))));
    }

    private void checkExpression(String expression, double expectedValue) {
        Assert.assertEquals(expectedValue, ExpressionParser.parseExpression(expression).evaluate(), DELTA);
    }

}
