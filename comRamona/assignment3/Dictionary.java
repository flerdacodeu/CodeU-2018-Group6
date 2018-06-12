package assignment3;

import java.util.HashMap;
import java.util.Map;

/**
 * Dictionary object that is constricted by adding words. It can be queried efficiently
 * to check if a string is a prefix or a word.
 * Implemented as a Trie data structure. https://en.wikipedia.org/wiki/Trie
 */
public class Dictionary {
    private Node root;

    public Dictionary() {
        root = new Node();
    }

    /**
     * Checks given prefix is a word contained in the dictionary. Case insensitive
     *
     * @param word string to query
     * @return true if word is contained in the dictionary
     */
    public boolean isWord(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.getChild(word.charAt(i));
            if (current == null) {
                return false;
            }
        }
        return current.isWord;
    }

    /**
     * Checks given string is prefix of a word in the dictionary. Case insensitive.
     * Assumes a complete word is also a valid prefix.
     *
     * @param prefix string to query
     * @return true if string is a prefix or a word
     */
    public boolean isPrefix(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            current = current.getChild(prefix.charAt(i));
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds word to dictionary
     *
     * @param word string to add
     */
    public void addWord(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.addChild(word.charAt(i));
        }
        current.isWord = true;

    }

    /**
     * Helper data structure for constructing the dictionary/trie
     */
    private class Node {
        boolean isWord;
        private Map<Character, Node> children; // could use an array of chars instead

        private Node() {
            children = new HashMap<>();
            isWord = false;
        }

        Node addChild(Character ch) {
            ch = Character.toUpperCase(ch);
            Node child = children.get(ch);
            if (child == null) {
                child = new Node();
                children.put(ch, child);
            }
            return child;
        }

        Node getChild(Character ch) {
            ch = Character.toUpperCase(ch);
            return children.get(ch);
        }
    }
}


