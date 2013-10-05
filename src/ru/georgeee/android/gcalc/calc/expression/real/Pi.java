package ru.georgeee.android.gcalc.calc.expression.real;

import ru.georgeee.android.gcalc.calc.Constants;
import ru.georgeee.android.gcalc.calc.GRealNumber;
import ru.georgeee.android.gcalc.calc.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:22
 * To change this template use File | Settings | File Templates.
 */
public class Pi extends Expression {
    @Override
    public GRealNumber getValue() {
        return Constants.PI;
    }
}
