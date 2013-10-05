package ru.georgeee.android.gcalc.calc.expression.real.atrig;

import ru.georgeee.android.gcalc.calc.GRealNumber;
import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.expression.RealUnaryOperator;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:10
 * To change this template use File | Settings | File Templates.
 */
public class ArcCos extends RealUnaryOperator {
    public ArcCos(Expression operand) {
        super(operand);
    }

    @Override
    public GRealNumber getValue() {
        return ((GRealNumber) operand.getValue()).arccos();
    }
}