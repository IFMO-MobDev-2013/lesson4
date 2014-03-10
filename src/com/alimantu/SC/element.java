package com.alimantu.SC;

/**
 * Created with IntelliJ IDEA.
 * User: PWR
 * Date: 12.01.14
 * Time: 2:55
 * To change this template use File | Settings | File Templates.
 */
public class element
{
    public boolean numb;
    public boolean point;
    public boolean operation;
    public boolean first_zero;
    public boolean opening_bracket;
    public boolean closing_bracket;
    public boolean empty_str;
    public int bracket_count;

    element()
    {}

    public void create(boolean n, boolean p, boolean o, boolean f,
                       boolean ob, boolean cb, boolean e, int bc)
    {
        numb = n;
        point = p;
        operation = o;
        first_zero = f;
        opening_bracket = ob;
        closing_bracket = cb;
        empty_str = e;
        bracket_count = bc;
    }

}