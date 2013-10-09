package ru.georgeee.android.gcalc.calc.parser.token;

import ru.georgeee.android.gcalc.calc.expression.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 8:51
 * To change this template use File | Settings | File Templates.
 */
public class Token {
    protected List<TokenType> tokenTypes;
    protected Expression expression = null;
    protected TokenType matchedTokenType = null;

    public Token(TokenType ... tokenTypes) {
        this.tokenTypes = new ArrayList<TokenType>(tokenTypes.length);
        Collections.addAll(this.tokenTypes, tokenTypes);
    }

    public Token(List<TokenType> tokenTypes){
        this.tokenTypes = tokenTypes;
    }

    public boolean isExpressionComputed(){
        return expression != null;
    }

    public TokenType getFirstTokenType(){
        return tokenTypes.isEmpty()?null:tokenTypes.get(0);
    }

    public Expression computeExpression(Expression leftOperand, Expression rightOperand, int priority) {
        for(TokenType tokenType : tokenTypes){
            if(expression != null) break;
            if(tokenType.getPriority() != priority) continue;
            expression = tokenType.getExpression(leftOperand, rightOperand);
            matchedTokenType = tokenType;
        }
        return expression;
    }

    public Expression getExpression(){
        return expression;
    }

    public TokenType getMatchedTokenType() {
        return isExpressionComputed()?matchedTokenType:null;
    }
}
