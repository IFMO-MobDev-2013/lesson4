package ru.georgeee.android.gcalc.calc.expression.real.ahyp;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.RealUnaryOperator;
import ru.georgeee.android.gcalc.calc.number.GRealNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:59
 * To change this template use File | Settings | File Templates.
 */
public class ASinh extends RealUnaryOperator {
    public ASinh(Expression operand) {
        super(operand);
    }

    @Override
    public GRealNumber getValue() {
        return ((GRealNumber) operand.getValue()).asinh();
    }
}
