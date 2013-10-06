package ru.georgeee.android.gcalc.calc.expression.integer;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.IntegerBinaryOperator;
import ru.georgeee.android.gcalc.calc.number.GIntegerNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 3:01
 * To change this template use File | Settings | File Templates.
 */
public class Mod extends IntegerBinaryOperator {
    public Mod(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public GIntegerNumber getValue() {
        return ((GIntegerNumber) leftOperand.getValue()).mod(((GIntegerNumber) rightOperand.getValue()));
    }
}
