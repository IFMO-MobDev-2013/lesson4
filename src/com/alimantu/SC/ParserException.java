package com.alimantu.SC;

/**
 * Created with IntelliJ IDEA.
 * User: PWR
 * Date: 02.03.14
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class ParserException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errStr;

    public ParserException(String errStr) {
        super();
        this.errStr = errStr;
    }

    public String toString(){
        return this.errStr;
    }
}
