package ru.georgeee.android.gcalc.calc.expression.real.trig;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.RealUnaryOperator;
import ru.georgeee.android.gcalc.calc.number.GRealNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:13
 * To change this template use File | Settings | File Templates.
 */
public class Cot extends RealUnaryOperator {
    public Cot(Expression operand) {
        super(operand);
    }

    @Override
    public GRealNumber getValue() {
        return ((GRealNumber) operand.getValue()).cot();
    }
}