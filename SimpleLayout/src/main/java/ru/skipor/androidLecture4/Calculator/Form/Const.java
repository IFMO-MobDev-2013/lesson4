package ru.skipor.androidLecture4.Calculator.Form;

public class Const implements Form {
    private final double value;

    public Const(double value) {
        this.value = value;
    }

    public Double evaluate() {
        return this.value;
    }
}
