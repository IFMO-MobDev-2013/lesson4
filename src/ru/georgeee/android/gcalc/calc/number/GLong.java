package ru.georgeee.android.gcalc.calc.number;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:44
 * To change this template use File | Settings | File Templates.
 */
public class GLong extends GIntegerNumber {
    public static final GLong ZERO = new GLong(0);
    long value;
    int radix;

    public GLong(long value, int radix) {
        this.value = value;
        this.radix = radix;
    }

    public GLong(long value) {
        this(value, 10);
    }


    public long getValue() {
        return value;
    }

    private GLong getGLong(GNumber _operand) {
        if (_operand instanceof GLong) {
            GLong operand = (GLong) _operand;
            return operand;
        } else {
            throw new UnsupportedOperationException("Supports only GLong as operand!");
        }
    }

    @Override
    public GLong and(GIntegerNumber _operand) {
        return new GLong(value & getGLong(_operand).getValue());
    }

    @Override
    public GLong or(GIntegerNumber _operand) {
        return new GLong(value | getGLong(_operand).getValue());
    }

    @Override
    public GLong xor(GIntegerNumber _operand) {
        return new GLong(value ^ getGLong(_operand).getValue());
    }

    @Override
    public GLong lshift(GIntegerNumber _operand) {
        return new GLong(value << getGLong(_operand).getValue());
    }

    @Override
    public GLong rshift(GIntegerNumber _operand) {
        return new GLong(value >> getGLong(_operand).getValue());
    }

    @Override
    public GLong not() {
        return new GLong(~value);
    }

    @Override
    public GLong negate() {
        return new GLong(-value);
    }

    @Override
    public GLong add(GNumber _operand) {
        return new GLong(value + getGLong(_operand).getValue());
    }

    @Override
    public GLong subtract(GNumber _operand) {
        return new GLong(value - getGLong(_operand).getValue());
    }

    @Override
    public GLong multiply(GNumber _operand) {
        return new GLong(value * getGLong(_operand).getValue());
    }

    @Override
    public GLong divide(GNumber _operand) {
        return new GLong(value / getGLong(_operand).getValue());
    }

    @Override
    public GLong power(GNumber _operand) {
        return new GLong((long) Math.pow(value, getGLong(_operand).getValue()));
    }

    @Override
    public GLong factorial() {
        if (value < 0) throw new UnsupportedOperationException("Factorial's argument shouldn't be negative");
        long result = 1;
        for (int i = 2; i <= value; ++i) {
            result *= i;
        }
        return new GLong(result);
    }

    @Override
    public GIntegerNumber mod(GIntegerNumber _operand) {
        return new GLong(value % getGLong(_operand).getValue());
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public GLong parseFromString(String string) {
        return new GLong(Long.parseLong(string, radix), radix);
    }
}
