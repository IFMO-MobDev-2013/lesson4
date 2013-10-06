package ru.georgeee.android.gcalc.calc.expression.basic;

import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GNumber;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class Constant extends Expression {
    GNumber value;

    public Constant(GNumber value) {
        this.value = value;
    }

    public GNumber evaluate() {
        return value;
    }
}
