package ru.georgeee.android.gcalc.calc.parser.ahocorasick;

import ru.georgeee.android.gcalc.calc.parser.token.Token;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 4:16
 * To change this template use File | Settings | File Templates.
 */
public class TokenFinder {

    LinkedList<Token> tokens = new LinkedList<Token>();
    char [] expression;

    public TokenFinder(String _expression) {
        expression = _expression.toCharArray();
        tokens.add(new Token(0, expression.length));

    }

    protected AhoCorasicTrie trie = new AhoCorasicTrie();


}
