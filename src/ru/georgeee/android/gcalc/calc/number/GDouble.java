package ru.georgeee.android.gcalc.calc.number;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 2:25
 * To change this template use File | Settings | File Templates.
 */
public class GDouble extends GRealNumber {
    public static final GDouble ZERO = new GDouble(0);
    double value;

    public GDouble(double value) {
        this.value = value;
    }

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
    public GRealNumber acos() {
        return new GDouble(Math.acos(value));
    }

    @Override
    public GRealNumber acot() {
        return new GDouble(Math.PI / 2 - Math.atan(value));
    }

    @Override
    public GRealNumber atan() {
        return new GDouble(Math.atan(value));
    }

    @Override
    public GRealNumber exp() {
        return new GDouble(Math.exp(value));
    }

    @Override
    public GRealNumber sqrt() {
        return new GDouble(Math.sqrt(value));
    }

    @Override
    public GRealNumber asin() {
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
    public GRealNumber sinh() {
        return new GDouble(Math.sinh(value));
    }

    @Override
    public GRealNumber cosh() {
        return new GDouble(Math.cosh(value));
    }

    @Override
    public GRealNumber tanh() {
        return new GDouble(Math.tanh(value));
    }

    @Override
    public GRealNumber coth() {
        return new GDouble(1 / Math.tanh(value));
    }

    @Override
    public GRealNumber asinh() {
        return new GDouble(Math.log(value + Math.sqrt(value * value + 1)));
    }

    @Override
    public GRealNumber acosh() {
        return new GDouble(Math.log(value + Math.sqrt(value * value - 1)));
    }

    @Override
    public GRealNumber atanh() {
        return new GDouble(0.5 * Math.log((1 + value) / (1 - value)));
    }

    @Override
    public GRealNumber acoth() {
        return new GDouble(0.5 * Math.log((1 + value) / (value - 1)));
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

    @Override
    public GDouble parseFromString(String string) {
        return new GDouble(Double.parseDouble(string));
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.#########");//9 points
        return decimalFormat.format(value);
    }
}
