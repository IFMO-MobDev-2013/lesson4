package ru.georgeee.android.gcalc.calc.parser.token;

import ru.georgeee.android.gcalc.calc.exception.WrongOperandsException;
import ru.georgeee.android.gcalc.calc.expression.Expression;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class RightAssocTokenType implements TokenType {
    @Override
    public Expression getExpression(Expression leftOperand, Expression rightOperand) {
        if (rightOperand == null) {
            throw new WrongOperandsException("Right operand passed null for right assoc operator: " + getClass().toString());
        }
        return getExpressionImpl(rightOperand);
    }

    protected abstract Expression getExpressionImpl(Expression rightOperand);

    @Override
    public boolean isLeftOperandUsed() {
        return false;
    }

    @Override
    public boolean isRightOperandUsed() {
        return true;
    }
}
