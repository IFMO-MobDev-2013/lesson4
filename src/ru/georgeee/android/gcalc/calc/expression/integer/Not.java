package ru.georgeee.android.gcalc.calc.expression.integer;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GIntegerNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:41
 * To change this template use File | Settings | File Templates.
 */
public class Not extends IntegerUnaryOperator {
    public Not(Expression operand) {
        super(operand);
    }

    @Override
    public GIntegerNumber evaluate() {
        return ((GIntegerNumber) operand.evaluate()).not();
    }
}
