package ru.georgeee.android.gcalc.calc.expression.basic;

import ru.georgeee.android.gcalc.calc.GNumber;
import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.UnaryOperator;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:21
 * To change this template use File | Settings | File Templates.
 */
public class Negate extends UnaryOperator {
    public Negate(Expression operand) {
        super(operand);
    }

    @Override
    public GNumber getValue() {
        return operand.getValue().negate();
    }
}