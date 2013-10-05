package ru.georgeee.android.gcalc.calc.expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntegerBinaryOperator extends IntegerExpression {
    protected Expression leftOperand, rightOperand;

    public Expression getLeftOperand() {
        return leftOperand;
    }

    public Expression getRightOperand() {
        return rightOperand;
    }

    public IntegerBinaryOperator(Expression leftOperand, Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

}
