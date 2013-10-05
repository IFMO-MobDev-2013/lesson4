package ru.georgeee.android.gcalc.calc.expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntegerUnaryOperator extends IntegerExpression {
    protected Expression operand;

    public IntegerUnaryOperator(Expression operand) {
        this.operand = operand;
    }
}