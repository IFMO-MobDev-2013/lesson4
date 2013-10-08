package ru.zulyaev.ifmo.lesson4;

import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;
import ru.zulyaev.ifmo.lesson4.parser.ExpressionParser;
import ru.zulyaev.ifmo.lesson4.parser.VariableValues;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;
import ru.zulyaev.ifmo.lesson4.parser.exception.ExpressionMalformed;

import java.util.Arrays;
import java.util.List;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ru.zulyaev.ifmo.lesson4.MainActivityTest \
 * ru.zulyaev.ifmo.lesson4.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final List<CorrectExpression> CORRECT_EXPRESSIONS = Arrays.asList(
            new CorrectExpression("1+2×3÷(3÷3)", 7),
            new CorrectExpression("2.5+3.5-0.5×4", 4),
            new CorrectExpression("-1+36", 35),
            new CorrectExpression("(2×√9)-1", 5),
            new CorrectExpression("2^3^2", 512),
            new CorrectExpression("2×π^2", 2 * Math.PI * Math.PI),
            new CorrectExpression("ln(e)", 1),
            new CorrectExpression("abs(sin(-π÷2))", 1)
    );
    private static final List<IncorrectExpression> INCORRECT_EXPRESSIONS = Arrays.asList(
            new IncorrectExpression("1+", ExpressionMalformed.class),
            new IncorrectExpression("(2+3×(4+5)", ExpressionMalformed.class),
            new IncorrectExpression("--25", ExpressionMalformed.class),
            new IncorrectExpression("2√3", ExpressionMalformed.class),
            new IncorrectExpression("sin3", ExpressionMalformed.class),
            new IncorrectExpression("25++61", ExpressionMalformed.class),
            new IncorrectExpression("2..5", ExpressionMalformed.class),
            new IncorrectExpression(".51", ExpressionMalformed.class)
    );

    private ExpressionParser<Double> parser;
    private VariableValues<Double> values;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        parser = getActivity().parser;
        values = getActivity().constants;
    }

    public void testCorrect() throws Exception {
        for (CorrectExpression expression : CORRECT_EXPRESSIONS) {
            Assert.assertEquals(parser.parse(expression.expr).evaluate(values), expression.answer);
        }
    }

    public void testIncorrect() throws Exception {
        for (IncorrectExpression expression : INCORRECT_EXPRESSIONS) {
            try {
                parser.parse(expression.expr).evaluate(values);
                Assert.fail(expression + " is incorrect");
            } catch (ExpressionMalformed | EvaluationException e) {
                Assert.assertEquals(expression.expr, e.getClass(), expression.exception);
            }
        }
    }

    private static class CorrectExpression {
        String expr;
        double answer;

        private CorrectExpression(String expr, double answer) {
            this.expr = expr;
            this.answer = answer;
        }
    }

    private static class IncorrectExpression {
        String expr;
        Class<?> exception;

        private IncorrectExpression(String expr, Class<?> exception) {
            this.expr = expr;
            this.exception = exception;
        }
    }
}
