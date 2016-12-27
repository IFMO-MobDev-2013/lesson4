package com.java.android.dronov.calculator.Exceptions;

import java.security.DigestInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: dronov
 * Date: 08.10.13
 * Time: 1:51
 * To change this template use File | Settings | File Templates.
 */
public class DivisionByZero extends RuntimeException {

    public DivisionByZero(String message) {
        super(message);
    }

    public DivisionByZero() {
        this("");
    }
}
