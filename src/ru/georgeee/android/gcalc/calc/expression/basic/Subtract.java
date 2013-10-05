package ru.georgeee.android.gcalc.calc.expression.basic;

import ru.georgeee.android.gcalc.calc.GNumber;
import ru.georgeee.android.gcalc.calc.expression.BinaryOperator;
import ru.georgeee.android.gcalc.calc.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:42
 * To change this template use File | Settings | File Templates.
 */
public class Subtract extends BinaryOperator {
    public Subtract(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public GNumber getValue() {
        return leftOperand.getValue().subtract(rightOperand.getValue());
    }
}
