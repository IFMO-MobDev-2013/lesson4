package ru.georgeee.android.gcalc.calc.parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: georgeee
 * Date: 05.10.13
 * Time: 5:38
 * To change this template use File | Settings | File Templates.
 */
public class AhoCorasicTrie<T> {
    final TrieNode root;
    final ArrayList<TrieNode> trie = new ArrayList<TrieNode>();

    AhoCorasicTrie() {
        root = new TrieNode();
        trie.add(root);
        root.link = root;
    }

    void addWord(String word, T terminalVertexData) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; ++i)
            node = node.getOrCreateNode(chars[i]);
        node.pair = new Pair(word, terminalVertexData);
    }

    public class Pair {
        public final String word;
        public final T data;

        public Pair(String word, T data) {
            this.word = word;
            this.data = data;
        }
    }

    public class TrieNode {
        protected Pair pair = null;
        protected TrieNode closestTerminalLinkage = null;
        protected HashMap<Character, TrieNode> goCache = null;
        protected HashMap<Character, TrieNode> childs = null;
        protected TrieNode link = null;
        protected final TrieNode parent;
        protected final char parentChar;

        //For root
        public TrieNode() {
            parent = null;
            parentChar = '\0';
        }

        public TrieNode(TrieNode parent, char parentChar) {
            this.parent = parent;
            this.parentChar = parentChar;
        }

        public boolean hasChild(char ch) {
            return childs != null && childs.containsKey(ch);
        }

        public TrieNode getOrCreateNode(char ch) {
            if (hasChild(ch)) return childs.get(ch);
            if (childs == null) childs = new HashMap<Character, TrieNode>();
            TrieNode node = new TrieNode(this, ch);
            trie.add(node);
            childs.put(ch, node);
            return node;
        }

        public TrieNode getLink() {
            if (link != null) return link;
            if (parent == root) return (link = root);
            return (link = parent.getLink().go(parentChar));
        }

        public TrieNode go(char ch) {
            if (goCache != null && goCache.containsKey(ch)) return goCache.get(ch);
            if (goCache == null) goCache = new HashMap<Character, TrieNode>();
            TrieNode result;
            if (hasChild(ch)) {
                result = childs.get(ch);
            } else {
                result = this == root ? root : getLink().go(ch);
            }
            goCache.put(ch, result);
            return result;
        }

        protected TrieNode getClosestTerminalLinkage() {
            if (closestTerminalLinkage != null) return closestTerminalLinkage;
            closestTerminalLinkage = getLink();
            while (closestTerminalLinkage != root && !closestTerminalLinkage.isTerminal())
                closestTerminalLinkage = closestTerminalLinkage.getLink();
            return closestTerminalLinkage;
        }


        /**
         * Enumeration of matching lists of pairs, in length decreasing order
         *
         * @return
         */
        public Enumeration<Pair> getMatchingPairs() {
            final TrieNode firstNode = pair == null ? getClosestTerminalLinkage() : this;
            return new Enumeration<Pair>() {
                TrieNode node = firstNode;

                @Override
                public boolean hasMoreElements() {
                    return node != root;
                }

                @Override
                public Pair nextElement() {
                    TrieNode _node = node;
                    node = node.getClosestTerminalLinkage();
                    return _node.pair;
                }
            };
        }

        public Pair getLongestMatchingPair() {
            if (pair != null) return pair;
            TrieNode _node = getClosestTerminalLinkage();
            if (_node.pair == null) return null;
            return _node.pair;
        }

        public boolean isTerminal() {
            return pair != null;
        }
    }


}
