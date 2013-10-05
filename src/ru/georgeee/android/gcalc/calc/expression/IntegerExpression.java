package ru.georgeee.android.gcalc.calc.expression;

import ru.georgeee.android.gcalc.calc.GIntegerNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public abstract class IntegerExpression extends Expression {
    @Override
    public abstract GIntegerNumber getValue();
}
