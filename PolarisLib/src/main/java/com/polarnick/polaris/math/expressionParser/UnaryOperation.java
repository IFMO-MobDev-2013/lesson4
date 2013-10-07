package com.polarnick.polaris.math.expressionParser;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
abstract class UnaryOperation implements Evaluable {

    private final Evaluable argument;

    UnaryOperation(Evaluable argument) {
        this.argument = argument;
    }

    @Override
    public double evaluate() {
        return evaluate(argument.evaluate());
    }

    public abstract double evaluate(double argument);
}
