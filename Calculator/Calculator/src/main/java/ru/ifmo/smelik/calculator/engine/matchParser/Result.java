
package ru.ifmo.smelik.calculator.engine.matchParser;


class Result
{

    public double acc;
    public String rest;

    public Result(double v, String r) {
        this.acc = v;
        this.rest = r;
    }
}
