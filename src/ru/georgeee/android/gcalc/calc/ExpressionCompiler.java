package ru.georgeee.android.gcalc.calc;

import ru.georgeee.android.gcalc.calc.exception.EmptyBracketException;
import ru.georgeee.android.gcalc.calc.exception.TokenCompileException;
import ru.georgeee.android.gcalc.calc.exception.UnlinkedTokensException;
import ru.georgeee.android.gcalc.calc.exception.WrongBracketBalanceException;
import ru.georgeee.android.gcalc.calc.expression.Expression;
import ru.georgeee.android.gcalc.calc.parser.TokenFinder;
import ru.georgeee.android.gcalc.calc.parser.token.Token;
import ru.georgeee.android.gcalc.calc.parser.token.TokenHolder;
import ru.georgeee.android.gcalc.calc.parser.token.TokenType;

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
    TokenHolder tokenHolder;

    public ExpressionCompiler(String source, TokenHolder tokenHolder) {
        this.source = source;
        this.tokenHolder = tokenHolder;
    }

    public Expression compile() {
        if (result == null) {
            TokenFinder tokenFinder = new TokenFinder(source, tokenHolder);
            tokens = tokenFinder.getTokens();
            caret = 0;
            tokens.add(new Token(new TokenHolder.ClosingBracketTokenType()));
            result = recursiveDescentParseBrackets().getExpression();
        }
        return result;
    }

    protected Token recursiveDescentParseBrackets() {
        LinkedList<Token> tokens = new LinkedList<Token>();
        //Adding tokens to list
        while (true) {
            if (caret >= this.tokens.size()) throw new WrongBracketBalanceException();
            Token token = this.tokens.get(caret++);
            if (token.getFirstTokenType() instanceof TokenHolder.ClosingBracketTokenType) {
                break;
            } else {
                if (token.getFirstTokenType() instanceof TokenHolder.OpenningBracketTokenType) {
                    tokens.add(recursiveDescentParseBrackets());
                } else {
                    tokens.add(token);
                }
            }
        }
        for (int priority = 0; priority <= tokenHolder.getMaxLevel(); ++priority) {
            boolean isRightAssoc = tokenHolder.isLevelRightAssociative(priority);
            if (priority == tokenHolder.getMultiplyLevel())
                for (int i = tokens.size() - 2; i >= 0; --i) {
                    Token current = tokens.get(i);
                    Token next = tokens.get(i + 1);
                    if (current.isExpressionComputed() && next.isExpressionComputed()) {
                        tokens.add(i + 1, new Token(tokenHolder.multiplyTokenType));
                    }
                }
                for (int _i = 0; _i < tokens.size(); ++_i) {
                    int i = isRightAssoc?tokens.size()-_i-1:_i;
                    Token token = tokens.get(i);
                    if(token.isExpressionComputed()) continue;
                    Expression leftOperand = i == 0 ? null : tokens.get(i - 1).getExpression();
                    Expression rightOperand = i == tokens.size() - 1 ? null : tokens.get(i + 1).getExpression();
                    token.computeExpression(leftOperand, rightOperand, priority);
                    if (token.isExpressionComputed()) {
                        TokenType matchedTokenType = token.getMatchedTokenType();
                        if (matchedTokenType.getArgumentType() == TokenType.BOTH_ARG_TYPE) {
                            tokens.remove(i + 1);
                            tokens.remove(--i);
                        } else if (matchedTokenType.getArgumentType() == TokenType.ONE_ARG_TYPE) {
                            tokens.remove(isRightAssoc?i+1:--i);
                        }
                    }
                    _i = isRightAssoc?tokens.size()-i-1:i;
                }
        }
        if (tokens.isEmpty()) throw new EmptyBracketException();
        if (tokens.size() > 1) throw new UnlinkedTokensException();
        if (!tokens.getFirst().isExpressionComputed()) throw new TokenCompileException("Null expression resulted");
        return tokens.getFirst();
    }

}
