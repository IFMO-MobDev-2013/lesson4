package ru.zulyaev.ifmo.lesson4.parser.exception;

public class VariableValueNotSpecified extends EvaluationException {
	public VariableValueNotSpecified(String variable) {
		super("Variable '" + variable + "' value not specified");
	}
}
