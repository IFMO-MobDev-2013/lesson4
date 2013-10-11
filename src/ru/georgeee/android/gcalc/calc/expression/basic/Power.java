package ru.georgeee.android.gcalc.calc.expression.basic;

import ru.georgeee.android.gcalc.calc.expression.BinaryOperator;
import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:50
 * To change this template use File | Settings | File Templates.
 */
public class Power extends BinaryOperator {
    public Power(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public GNumber evaluate() {
        return leftOperand.evaluate().power(rightOperand.evaluate());
    }
}
