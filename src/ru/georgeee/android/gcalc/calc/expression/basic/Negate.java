package ru.georgeee.android.gcalc.calc.expression.basic;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.UnaryOperator;
import ru.georgeee.android.gcalc.calc.number.GNumber;

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
    public GNumber evaluate() {
        return operand.evaluate().negate();
    }
}