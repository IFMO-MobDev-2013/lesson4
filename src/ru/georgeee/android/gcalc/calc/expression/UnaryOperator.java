package ru.georgeee.android.gcalc.calc.expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class UnaryOperator extends Expression {
    protected Expression operand;

    public UnaryOperator(Expression operand) {
        this.operand = operand;
    }
}
