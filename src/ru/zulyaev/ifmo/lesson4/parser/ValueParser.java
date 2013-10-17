package ru.zulyaev.ifmo.lesson4.parser;

public interface ValueParser<E> {
    /**
     * Parse specified token and return value that token represents
     *
     * @param token token to parse
     * @return value of expression
     */
    E parse(String token);
}
