package ru.georgeee.android.gcalc.calc.expression.integer;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.IntegerUnaryOperator;
import ru.georgeee.android.gcalc.calc.number.GIntegerNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:52
 * To change this template use File | Settings | File Templates.
 */
public class Factorial extends IntegerUnaryOperator {
    public Factorial(Expression operand) {
        super(operand);
    }

    @Override
    public GIntegerNumber getValue() {
        return ((GIntegerNumber) operand.getValue()).factorial();
    }
}
