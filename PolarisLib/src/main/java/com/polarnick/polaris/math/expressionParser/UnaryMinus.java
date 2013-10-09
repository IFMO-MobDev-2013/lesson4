package com.polarnick.polaris.math.expressionParser;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
class UnaryMinus extends UnaryOperation {

    UnaryMinus(Evaluable argument) {
        super(argument);
    }

    @Override
    public double evaluate(double argument) {
        return -argument;
    }
}