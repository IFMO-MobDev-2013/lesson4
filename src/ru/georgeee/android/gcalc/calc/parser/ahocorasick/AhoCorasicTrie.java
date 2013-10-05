package ru.georgeee.android.gcalc.calc.parser.ahocorasick;

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
public class AhoCorasicTrie {
    final TrieNode root;
    final ArrayList<TrieNode> trie = new ArrayList<TrieNode>();

    AhoCorasicTrie() {
        root = new TrieNode();
        trie.add(root);
        root.link = root;
    }

    void addWord(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < chars.length; ++i)
            node = node.getOrCreateNode(chars[i]);
        node.word = word;
    }

    void addWords(String[] words) {
        for (String word : words) {
            addWord(word);
        }
    }

    public class TrieNode {
        String word = null;
        TrieNode closestTerminalLinkage = null;
        HashMap<Character, TrieNode> goCache = null;
        HashMap<Character, TrieNode> childs = null;
        TrieNode link = null;
        TrieNode parent = null;
        char parentChar = '\0';

        public TrieNode() {
        }

        public boolean hasChild(char ch) {
            return childs != null && childs.containsKey(ch);
        }

        public TrieNode getOrCreateNode(char ch) {
            if (hasChild(ch)) return childs.get(ch);
            if (childs == null) childs = new HashMap<Character, TrieNode>();
            TrieNode node = new TrieNode();
            node.parent = this;
            node.parentChar = ch;
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

        TrieNode getClosestTerminalLinkage() {
            if (closestTerminalLinkage != null) return closestTerminalLinkage;
            closestTerminalLinkage = getLink();
            while (closestTerminalLinkage != root && !closestTerminalLinkage.isTerminal())
                closestTerminalLinkage = closestTerminalLinkage.getLink();
            return closestTerminalLinkage;
        }

        /**
         * Enumeration of matching words, in length decreasing order
         *
         * @return
         */
        public Enumeration<String> getMatchingWords() {
            final TrieNode firstNode = word == null ? getClosestTerminalLinkage() : this;
            return new Enumeration<String>() {
                TrieNode node = firstNode;

                @Override
                public boolean hasMoreElements() {
                    return node != root;
                }

                @Override
                public String nextElement() {
                    TrieNode _node = node;
                    node = node.getClosestTerminalLinkage();
                    return _node.word;
                }
            };
        }

        public String getLongestMatchingWord() {
            if (word != null) return word;
            return getClosestTerminalLinkage().word;
        }

        boolean isTerminal() {
            return word != null;
        }
    }


}
