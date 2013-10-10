package ru.skipor.androidLecture4.Calculator.Form;

/**
 * Created with IntelliJ IDEA.
 * User: vladimirskipor
 * Date: 28.05.13
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class FormEvaluationException extends Exception{
    public FormEvaluationException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public FormEvaluationException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
