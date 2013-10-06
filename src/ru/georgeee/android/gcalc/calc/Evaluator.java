package ru.georgeee.android.gcalc.calc;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GBigInt;
import ru.georgeee.android.gcalc.calc.number.GDouble;
import ru.georgeee.android.gcalc.calc.number.GLong;
import ru.georgeee.android.gcalc.calc.number.GNumber;

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
        return new ExpressionCompiler(source, numberModel).compile();
    }

    public static double getDoubleValue(String source) {
        return ((GDouble) getExpression(source, GDouble.ZERO).getValue()).getValue();
    }

    public static long getLongValue(String source, int radix) {
        return ((GLong) getExpression(source, new GLong(0, radix)).getValue()).getValue();
    }

    public static BigInteger getBigIntegerValue(String source, int radix) {
        return ((GBigInt) getExpression(source, new GBigInt(BigInteger.ZERO, radix)).getValue()).getValue();
    }

    public static long getLongValue(String source) {
        return getLongValue(source, 10);
    }

    public static BigInteger getBigIntegerValue(String source) {
        return getBigIntegerValue(source, 10);
    }

}
