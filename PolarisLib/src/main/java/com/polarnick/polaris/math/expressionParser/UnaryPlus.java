package com.polarnick.polaris.math.expressionParser;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
class UnaryPlus extends UnaryOperation {

    UnaryPlus(Evaluable argument) {
        super(argument);
    }

    @Override
    public double evaluate(double argument) {
        return argument;
    }
}