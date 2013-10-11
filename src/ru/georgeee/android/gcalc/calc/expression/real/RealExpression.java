package ru.georgeee.android.gcalc.calc.expression.real;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GRealNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class RealExpression extends Expression {
    @Override
    public abstract GRealNumber evaluate();
}
