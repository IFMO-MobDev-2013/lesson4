package com.polarnick.polaris.math.expressionParser;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
class Constant implements Evaluable {

    private final double value;

    Constant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }
}