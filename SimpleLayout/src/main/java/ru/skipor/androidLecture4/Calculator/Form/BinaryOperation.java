package ru.skipor.androidLecture4.Calculator.Form;

public class BinaryOperation implements Form {
    private final Form leftArgument;
    private final Form rightArgument;
    private final Operation operation;

    public BinaryOperation(Form leftArgument, Form rightArgument, Operation operation) {
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
        this.operation = operation;
    }

    public Double evaluate() throws FormEvaluationException {
        return operation.apply(leftArgument.evaluate(), rightArgument.evaluate());
    }


}

