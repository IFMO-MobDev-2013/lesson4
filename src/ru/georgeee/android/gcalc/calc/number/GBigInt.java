package ru.georgeee.android.gcalc.calc.number;

import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:44
 * To change this template use File | Settings | File Templates.
 */
public class GBigInt extends GIntegerNumber {

    public static final GBigInt ZERO = new GBigInt(BigInteger.ZERO);
    BigInteger value;
    final int radix;

    public GBigInt(BigInteger value, int radix) {
        this.value = value;
        this.radix = radix;
    }

    public GBigInt(BigInteger value) {
        this(value, 10);
    }

    public BigInteger getValue() {
        return value;
    }

    private GBigInt getGBigInt(GNumber _operand) {
        if (_operand instanceof GBigInt) {
            GBigInt operand = (GBigInt) _operand;
            return operand;
        } else if (_operand instanceof GLong) {
            GBigInt operand = new GBigInt(BigInteger.valueOf(((GLong) _operand).getValue()));
            return operand;
        } else {
            throw new UnsupportedOperationException("Supports only GBigInt/GLong as operand!");
        }
    }

    @Override
    public GBigInt and(GIntegerNumber _operand) {
        return new GBigInt(value.and(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt or(GIntegerNumber _operand) {
        return new GBigInt(value.or(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt xor(GIntegerNumber _operand) {
        return new GBigInt(value.xor(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt lshift(GIntegerNumber _operand) {
        return new GBigInt(value.shiftLeft(getGBigInt(_operand).getValue().intValue()));
    }

    @Override
    public GBigInt rshift(GIntegerNumber _operand) {
        return new GBigInt(value.shiftRight(getGBigInt(_operand).getValue().intValue()));
    }

    @Override
    public GBigInt not() {
        return new GBigInt(value.not());
    }

    @Override
    public GBigInt negate() {
        return new GBigInt(value.negate());
    }

    @Override
    public GBigInt add(GNumber _operand) {
        return new GBigInt(value.add(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt subtract(GNumber _operand) {
        return new GBigInt(value.subtract(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt multiply(GNumber _operand) {
        return new GBigInt(value.multiply(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt divide(GNumber _operand) {
        return new GBigInt(value.divide(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt power(GNumber _operand) {
        return new GBigInt(value.pow(getGBigInt(_operand).getValue().intValue()));
    }

    @Override
    public GBigInt factorial() {
        if (value.compareTo(BigInteger.ZERO) == -1)
            throw new UnsupportedOperationException("Factorial's argument shouldn't be negative");
        int val = value.intValue();
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= val; ++i) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return new GBigInt(result);
    }

    @Override
    public GIntegerNumber mod(GIntegerNumber _operand) {
        return new GBigInt(value.mod(getGBigInt(_operand).getValue()));
    }

    @Override
    public GBigInt parseFromString(String string) {
        return new GBigInt(new BigInteger(string, radix), radix);
    }

    @Override
    public String toString() {
        return value.toString(radix);
    }
}
