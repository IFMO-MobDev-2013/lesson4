package com.polarnick.polaris.math.expressionParser;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
class Minus extends BinaryOperation {

    Minus(Evaluable firstArgument, Evaluable secondArgument) {
        super(firstArgument, secondArgument);
    }

    @Override
    public double evaluate(double firstArgument, double secondArgument) {
        return firstArgument - secondArgument;
    }
}