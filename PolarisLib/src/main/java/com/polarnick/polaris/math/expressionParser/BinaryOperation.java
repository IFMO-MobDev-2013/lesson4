package com.polarnick.polaris.math.expressionParser;

/**
 * Date: 08.10.13
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
abstract class BinaryOperation implements Evaluable {

    private final Evaluable firstArgument;
    private final Evaluable secondArgument;

    BinaryOperation(Evaluable firstArgument, Evaluable secondArgument) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    @Override
    public double evaluate() {
        return evaluate(firstArgument.evaluate(), secondArgument.evaluate());
    }

    public abstract double evaluate(double firstArgument, double secondArgument);
}
