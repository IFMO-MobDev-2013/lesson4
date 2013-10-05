package ru.georgeee.android.gcalc.calc.parser.token;

import ru.georgeee.android.gcalc.calc.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 5:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class TokenType {
    public abstract Expression getExpression(Expression leftOperand, Expression rightOperand);

    public abstract boolean isLeftOperandUsed();

    public abstract boolean isRightOperandUsed();

    public abstract int getPriority();
}
