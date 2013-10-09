package ru.georgeee.android.gcalc.calc.parser;

import ru.georgeee.android.gcalc.calc.exception.UnknownTokenException;
import ru.georgeee.android.gcalc.calc.parser.token.*;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 4:16
 * To change this template use File | Settings | File Templates.
 */
public class TokenFinder {

    final protected char[] expressionChars;
    final protected String expression;
    protected LinkedList<Token> tokens = new LinkedList<Token>();
    protected ArrayList<AhoCorasicTrie<List<AhoTokenType>>.Pair> pos;
    protected AhoCorasicTrie<List<AhoTokenType>> trie = new AhoCorasicTrie<List<AhoTokenType>>();
    protected TokenHolder tokenHolder;

    public TokenFinder(String expression, TokenHolder tokenHolder) {
        this.expression = expression;
        expressionChars = expression.toCharArray();
        pos = new ArrayList<AhoCorasicTrie<List<AhoTokenType>>.Pair>(expressionChars.length);
        this.tokenHolder = tokenHolder;
    }

    protected void init() {
        HashMap<String, ArrayList<AhoTokenType>> tokenTypeMap = new HashMap<String, ArrayList<AhoTokenType>>();
        for (AhoTokenType tokenType : tokenHolder.getAhoTokenTypes()) {
                String word = tokenType.getMatchString();
                if(!tokenTypeMap.containsKey(word)) tokenTypeMap.put(word, new ArrayList<AhoTokenType>());
                tokenTypeMap.get(word).add(tokenType);
        }
        for(String word:tokenTypeMap.keySet()){
            trie.addWord(word, tokenTypeMap.get(word));
        }
    }

    protected void processAho() {
        AhoCorasicTrie<List<AhoTokenType>>.TrieNode node = trie.root;
        for (int i = 0; i < expressionChars.length; ++i) {
            node = node.go(expressionChars[i]);
            AhoCorasicTrie<List<AhoTokenType>>.Pair pair = node.getLongestMatchingPair();
            pos.add(pair);
        }
    }

    protected TokenType getManual(String part) {
        part = part.trim();
        if (part.isEmpty()) return null;
        TokenType tokenType;
        for (ManualTokenTypeFactory factory : tokenHolder.getManualTokenTypeFabrics()) {
            if ((tokenType = factory.getTokenType(part)) != null) return tokenType;
        }
        throw new UnknownTokenException("Unknown token: " + part);
    }

    protected void computeTokens() {
        for (int j = expressionChars.length - 1; j >= 0; --j) {
            int i;
            if (pos.get(j) != null) {
                i = j + 1 - pos.get(j).word.length();
                ArrayList<TokenType> list = new ArrayList<TokenType>();
                for(AhoTokenType tokenType: pos.get(j).data) list.add(tokenType);
                tokens.add(new Token(list));
            } else {
                i = j;
                while (i > 0 && pos.get(i-1) == null) --i;
                String manualPart = expression.substring(i, j + 1);
                TokenType manual = getManual(manualPart);
                if (manual != null) tokens.add(new Token(manual));
            }
            j = i;
        }
        Collections.reverse(tokens);
    }

    public List<Token> getTokens() {
        if (tokens != null) {
            init();
            processAho();
            computeTokens();
        }
        return tokens;
    }


}
