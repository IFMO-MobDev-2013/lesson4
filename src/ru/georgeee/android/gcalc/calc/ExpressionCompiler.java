package ru.georgeee.android.gcalc.calc;

import ru.georgeee.android.gcalc.calc.exception.EmptyBracketException;
import ru.georgeee.android.gcalc.calc.exception.TokenCompileException;
import ru.georgeee.android.gcalc.calc.exception.UnlinkedTokensException;
import ru.georgeee.android.gcalc.calc.exception.WrongBracketBalanceException;
import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.number.GNumber;
import ru.georgeee.android.gcalc.calc.parser.TokenFinder;
import ru.georgeee.android.gcalc.calc.parser.token.Token;
import ru.georgeee.android.gcalc.calc.parser.token.Tokens;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionCompiler {
    String source;
    List<Token> tokens = null;
    Expression result = null;
    int caret;
    GNumber number;
    int maxPriority = -1;

    public ExpressionCompiler(String source, GNumber number) {
        this.source = source;
        this.number = number;
    }

    public Expression compile() {
        if (result == null) {
            TokenFinder tokenFinder = new TokenFinder(source, number);
            tokens = tokenFinder.getTokens();
            for (Token token : tokens) {
                int priority = token.type.getPriority();
                if (priority > maxPriority) maxPriority = priority;
            }
            if (maxPriority < Tokens.MULTIPLY_LEVEL)
                maxPriority = Tokens.MULTIPLY_LEVEL;
            caret = 0;
            tokens.add(new Token(new Tokens.ClosingBracketTokenType()));
            result = recursiveDescentParseBrackets().expression;
        }
        return result;
    }

    protected Token recursiveDescentParseBrackets() {
        LinkedList<Token> tokens = new LinkedList<Token>();
        //Adding tokens to list
        while (true) {
            if (caret >= this.tokens.size()) throw new WrongBracketBalanceException();
            Token token = this.tokens.get(caret++);
            if (token.type instanceof Tokens.ClosingBracketTokenType) {
                break;
            } else {
                if (token.type instanceof Tokens.OpenningBracketTokenType) {
                    tokens.add(recursiveDescentParseBrackets());
                } else {
                    tokens.add(token);
                }
            }
        }
        for (int priority = 0; priority <= maxPriority; ++priority) {
            for (int i = tokens.size() - 2; i >= 0; --i) {
                Token current = tokens.get(i);
                Token next = tokens.get(i + 1);
                if (current.expression != null && next.expression != null) {
                    tokens.add(i + 1, new Token(Tokens.MULTIPLY_TOKEN_TYPE));
                }
            }
            for (int i = 0; i < tokens.size(); ++i) {
                Token token = tokens.get(i);
                if (token.type.getPriority() == priority) {
                    Expression leftOperand = i == 0 ? null : tokens.get(i - 1).expression;
                    Expression rightOperand = i == tokens.size() - 1 ? null : tokens.get(i + 1).expression;
                    token.computeExpression(leftOperand, rightOperand);
                    if (rightOperand != null && token.type.isRightOperandUsed()) tokens.remove(i + 1);
                    if (leftOperand != null && token.type.isLeftOperandUsed()) tokens.remove(--i);
                }
            }
        }
        if (tokens.isEmpty()) throw new EmptyBracketException();
        if (tokens.size() > 1) throw new UnlinkedTokensException();
        if (tokens.getFirst().expression == null) throw new TokenCompileException("Null expression resulted");
        return tokens.getFirst();
    }

}
