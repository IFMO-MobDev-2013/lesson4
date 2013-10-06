package ru.georgeee.android.gcalc.calc.expression.integer;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.IntegerBinaryOperator;
import ru.georgeee.android.gcalc.calc.number.GIntegerNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:38
 * To change this template use File | Settings | File Templates.
 */
public class Or extends IntegerBinaryOperator {
    public Or(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public GIntegerNumber evaluate() {
        return ((GIntegerNumber) leftOperand.evaluate()).or((GIntegerNumber) (rightOperand.evaluate()));
    }
}
