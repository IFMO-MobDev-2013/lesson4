package ru.georgeee.android.gcalc.calc;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 2:25
 * To change this template use File | Settings | File Templates.
 */
public class GDouble extends GRealNumber {
    double value;

    public double getValue() {
        return value;
    }

    private GDouble getGDouble(GNumber _operand) {
        if (_operand instanceof GDouble) {
            GDouble operand = (GDouble) _operand;
            return operand;
        } else {
            throw new UnsupportedOperationException("Supports only GBigInt/GLong as operand!");
        }
    }

    public GDouble(double value) {
        this.value = value;
    }

    @Override
    public GRealNumber sin() {
        return new GDouble(Math.sin(value));
    }

    @Override
    public GRealNumber cos() {
        return new GDouble(Math.cos(value));
    }

    @Override
    public GRealNumber tan() {
        return new GDouble(Math.tan(value));
    }

    @Override
    public GRealNumber cot() {
        return new GDouble(1d / Math.tan(value));
    }

    @Override
    public GRealNumber arccos() {
        return new GDouble(Math.acos(value));
    }

    @Override
    public GRealNumber arccot() {
        return new GDouble(Math.PI / 2 - Math.atan(value));
    }

    @Override
    public GRealNumber arctan() {
        return new GDouble(Math.atan(value));
    }

    @Override
    public GRealNumber exp() {
        return new GDouble(Math.exp(value));
    }

    @Override
    public GRealNumber arcsin() {
        return new GDouble(Math.asin(value));
    }

    @Override
    public GRealNumber ln() {
        return new GDouble(Math.log(value));
    }

    @Override
    public GRealNumber log2() {
        return new GDouble(Math.log(value) / Math.log(2));
    }

    @Override
    public GRealNumber lg() {
        return new GDouble(Math.log10(value));
    }

    @Override
    public GNumber negate() {
        return new GDouble(-value);
    }

    @Override
    public GNumber add(GNumber operand) {
        return new GDouble(value + getGDouble(operand).getValue());
    }

    @Override
    public GNumber subtract(GNumber operand) {
        return new GDouble(value - getGDouble(operand).getValue());
    }

    @Override
    public GNumber multiply(GNumber operand) {
        return new GDouble(value * getGDouble(operand).getValue());
    }

    @Override
    public GNumber divide(GNumber operand) {
        return new GDouble(value / getGDouble(operand).getValue());
    }

    @Override
    public GNumber power(GNumber operand) {
        return new GDouble(Math.pow(value, getGDouble(operand).getValue()));
    }

    public static GDouble parseFromString(String string) {
        return new GDouble(Double.parseDouble(string));
    }
}
