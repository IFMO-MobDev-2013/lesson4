package ru.zulyaev.ifmo.lesson4.parser.evaluable;

import ru.zulyaev.ifmo.lesson4.parser.Evaluable;

interface EvaluableOperator<E> extends Evaluable<E> {
    int getPriority();
}
