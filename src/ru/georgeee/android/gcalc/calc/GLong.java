package ru.georgeee.android.gcalc.calc;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:44
 * To change this template use File | Settings | File Templates.
 */
public class GLong extends GIntegerNumber {
    long value;

    public GLong(long value) {
        this.value = value;
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

    public static GNumber parseFromString(String string) {
        return new GLong(Long.parseLong(string));
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
        if (value > 20) throw new UnsupportedOperationException("Factorial's argument shouldn't exceed 20");
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
}
