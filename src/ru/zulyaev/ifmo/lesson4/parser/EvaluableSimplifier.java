package ru.zulyaev.ifmo.lesson4.parser;

import ru.zulyaev.ifmo.lesson4.parser.evaluable.EvaluableBinaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.evaluable.EvaluableFunction;
import ru.zulyaev.ifmo.lesson4.parser.evaluable.EvaluableUnaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.exception.EvaluationException;

public interface EvaluableSimplifier<E> {

	Evaluable<E> simplifyBinary(EvaluableBinaryOperator<E> binary) throws EvaluationException;

	Evaluable<E> simplifyUnary(EvaluableUnaryOperator<E> unary) throws EvaluationException;

	Evaluable<E> simplifyFunction(EvaluableFunction<E> function) throws EvaluationException;
}
