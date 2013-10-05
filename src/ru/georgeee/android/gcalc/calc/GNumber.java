package ru.georgeee.android.gcalc.calc;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 0:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class GNumber {
    public static GNumber parseFromString(String string){
        return null;
    }

    public abstract GNumber negate();

    public abstract GNumber add(GNumber value);

    public abstract GNumber subtract(GNumber value);

    public abstract GNumber multiply(GNumber value);

    public abstract GNumber divide(GNumber value);

    public abstract GNumber power(GNumber value);

}
