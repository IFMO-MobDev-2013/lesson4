package ru.georgeee.android.gcalc.calc.expression.real;

import ru.georgeee.android.gcalc.calc.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
public abstract class RealUnaryOperator extends RealExpression {
    protected Expression operand;

    public RealUnaryOperator(Expression operand) {
        this.operand = operand;
    }
}