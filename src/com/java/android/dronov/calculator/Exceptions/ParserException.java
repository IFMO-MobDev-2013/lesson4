package com.java.android.dronov.calculator.Exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: dronov
 * Date: 08.10.13
 * Time: 1:21
 * To change this template use File | Settings | File Templates.
 */
public class ParserException extends RuntimeException {

    public ParserException(String message) {
        super(message);
    }
    public ParserException() {
        this("");
    }
}
