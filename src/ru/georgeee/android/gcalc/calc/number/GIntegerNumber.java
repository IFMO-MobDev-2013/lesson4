package ru.georgeee.android.gcalc.calc.number;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class GIntegerNumber extends GNumber {
    public abstract GIntegerNumber and(GIntegerNumber value);

    public abstract GIntegerNumber or(GIntegerNumber value);

    public abstract GIntegerNumber xor(GIntegerNumber value);

    public abstract GIntegerNumber lshift(GIntegerNumber value);

    public abstract GIntegerNumber rshift(GIntegerNumber value);

    public abstract GIntegerNumber not();

    public abstract GIntegerNumber factorial();

    public abstract GIntegerNumber mod(GIntegerNumber value);
}
