package ru.georgeee.android.gcalc.calc;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GBigInt;
import ru.georgeee.android.gcalc.calc.number.GDouble;
import ru.georgeee.android.gcalc.calc.number.GLong;
import ru.georgeee.android.gcalc.calc.number.GNumber;
import ru.georgeee.android.gcalc.calc.parser.token.TokenHolder;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 06.10.13
 * Time: 3:46
 * To change this template use File | Settings | File Templates.
 */
public class Evaluator {
    private static Expression getExpression(String source, GNumber numberModel) {
        return new ExpressionCompiler(source, new TokenHolder(numberModel)).compile();
    }

    public static GDouble evaluateDouble(String source) {
        return (GDouble) getExpression(source, GDouble.ZERO).evaluate();
    }

    public static GLong evaluateLong(String source, int radix) {
        return (GLong) getExpression(source, new GLong(0, radix)).evaluate();
    }

    public static GBigInt evaluateBigInt(String source, int radix) {
        return (GBigInt) getExpression(source, new GBigInt(BigInteger.ZERO, radix)).evaluate();
    }

    public static GLong evaluateLong(String source) {
        return evaluateLong(source, 10);
    }

    public static GBigInt evaluateBigInt(String source) {
        return evaluateBigInt(source, 10);
    }

}
