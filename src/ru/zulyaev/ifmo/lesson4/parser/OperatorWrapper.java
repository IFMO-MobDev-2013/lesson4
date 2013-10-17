package ru.zulyaev.ifmo.lesson4.parser;

import ru.zulyaev.ifmo.lesson4.parser.evaluable.EvaluableBinaryOperator;
import ru.zulyaev.ifmo.lesson4.parser.evaluable.EvaluableUnaryOperator;

class OperatorWrapper<E> implements Comparable<OperatorWrapper<E>> {
    private final int priority;
    private final String token;
    private final Operator<E> operator;

    OperatorWrapper(int priority, String token, Operator<E> operator) {
        this.priority = priority;
        this.token = token;
        this.operator = operator;
    }

    Evaluable<E> asEvaluable(Evaluable<E> one, boolean rightAssociative) {
        if (!isUnary()) {
            throw new IllegalStateException();
        }
        return new EvaluableUnaryOperator<>((UnaryOperator<E>) operator, priority, rightAssociative, one, token);
    }

    Evaluable<E> asEvaluable(Evaluable<E> left, Evaluable<E> right, boolean rightAssociative) {
        if (!isBinary()) {
            throw new IllegalStateException();
        }
        return new EvaluableBinaryOperator<>((BinaryOperator<E>) operator, priority, rightAssociative, left, right, token);
    }

    boolean isUnary() {
        return operator instanceof UnaryOperator;
    }

    boolean isBinary() {
        return operator instanceof BinaryOperator;
    }

    private String caseInsensitiveToken = null;

    String getToken(boolean caseSensitive) {
        if (caseSensitive) {
            return token;
        }
        if (caseInsensitiveToken == null) {
            caseInsensitiveToken = token.toLowerCase();
        }
        return caseInsensitiveToken;
    }

    @Override
    public String toString() {
        return "OperatorWrapper{" +
                "priority=" + priority +
                ", token='" + token + '\'' +
                '}';
    }

    int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(OperatorWrapper<E> o) {
        return priority - o.priority;
    }
}
