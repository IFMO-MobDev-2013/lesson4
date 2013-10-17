package ru.zulyaev.ifmo.lesson4.parser;

class FunctionWrapper<E> {
    private final Function<E> function;
    private final String token;

    FunctionWrapper(Function<E> function, String token) {
        this.function = function;
        this.token = token;
    }

    Function<E> getFunction() {
        return function;
    }

    String getToken() {
        return token;
    }
}
