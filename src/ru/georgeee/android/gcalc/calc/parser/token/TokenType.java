package ru.georgeee.android.gcalc.calc.parser.token;

import ru.georgeee.android.gcalc.calc.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 5:56
 * To change this template use File | Settings | File Templates.
 */
public interface TokenType {
    public static final int NO_ARG_TYPE = 1;
    public static final int ONE_ARG_TYPE = 2;
    public static final int BOTH_ARG_TYPE = 3;

    public Expression getExpression(Expression leftOperand, Expression rightOperand);

    public int getArgumentType();

    public int getPriority();
}
