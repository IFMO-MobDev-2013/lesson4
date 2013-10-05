package ru.georgeee.android.gcalc.calc;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 1:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class GRealNumber extends GNumber {

    public abstract GRealNumber sin();

    public abstract GRealNumber cos();

    public abstract GRealNumber tan();

    public abstract GRealNumber cot();

    public abstract GRealNumber arccos();

    public abstract GRealNumber arccot();

    public abstract GRealNumber arcsin();

    public abstract GRealNumber ln();

    public abstract GRealNumber log2();

    public abstract GRealNumber lg();

    public abstract GRealNumber arctan();

    public abstract GRealNumber exp();
}
