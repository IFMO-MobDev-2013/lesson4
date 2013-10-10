import com.ifmoctd.korolyov.Calculator.MyActivity;
import org.junit.Test;


import static org.junit.Assert.*;

public class TestModule {

    private static final double DELTA = 0.0000000001;

    @Test
    public void testTimes() {
        checkTest("1*1*1.1", 1 * 1 * 1.1);
    }

    @Test
    public void testSimpleDiv() {
        checkTest("-1/(-1)", -1 / (-1));
    }

    @Test
    public void testTimesComplex() {
        checkTest("1*2*3*4*5", 1 * 2 * 3 * 4 * 5);
    }

    @Test
    public void testTimesDiv() {
        checkTest("1*2*3*4*5/10", 1 * 2 * 3 * 4 * 5 / 10);
    }

    @Test
    public void testBrackets1() {
        checkTest("(2+2)*2", (2 + 2) * 2);
    }

    @Test
    public void testSimple() {
        checkTest("2+2*2", 2 + 2 * 2);
    }

    @Test
    public void testDivComplex() {
        checkTest("1/2/3+3", 1.0 / 2.0 / 3.0 + 3);
    }


    @Test
    public void testSuperComplexExpression() {
        checkTest("1/2/(3*2+3)", 1.0 / 2.0 / (3.0 * 2 + 3.0));
    }


    private void checkTest(String s, double res) {
        assertEquals(res, MyActivity.evaluate(s), DELTA);
    }

}