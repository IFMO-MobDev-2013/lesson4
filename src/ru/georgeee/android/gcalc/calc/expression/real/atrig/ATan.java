package ru.georgeee.android.gcalc.calc.expression.real.atrig;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.RealUnaryOperator;
import ru.georgeee.android.gcalc.calc.number.GRealNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public class ATan extends RealUnaryOperator {
    public ATan(Expression operand) {
        super(operand);
    }

    @Override
    public GRealNumber evaluate() {
        return ((GRealNumber) operand.evaluate()).atan();
    }
}