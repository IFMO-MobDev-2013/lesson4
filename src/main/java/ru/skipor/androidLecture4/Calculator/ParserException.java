package ru.skipor.androidLecture4.Calculator;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirskipor
 * Date: 26.05.13
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */
public class ParserException extends Exception {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Exception cause) {
        super(message, cause);
    }
}
