package ru.mermakov.projects.MCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: Mikhail
 * Date: 09.10.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class ParserException extends Exception {
    private static final long SERIAL_VERSION_UID = 1L;
    private String errString;

    public ParserException(String errString) {
        super();
        this.errString = errString;
    }

    public String toString() {
        return this.errString;
    }
}
