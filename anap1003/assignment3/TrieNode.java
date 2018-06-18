/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.HashMap;

/**
 *
 * @author Ana
 */
public class TrieNode {
    private Character letter;
    private boolean completeWord;
    private HashMap<Character, TrieNode> children;
    
    public TrieNode(Character letter, boolean completeWord) {
        this.letter = letter;
        this.completeWord = completeWord;
        children = new HashMap<>();
    }
    
    public boolean hasChild(Character c) {
        return children.containsKey(c);
    }
    
    public TrieNode moveToChild(Character c) {
        return children.get(c);
    }
    
    public TrieNode addChild(Character c, boolean isWord) {
        TrieNode child = new TrieNode(c, isWord);
        children.put(c, child);
        return child;
        
    }
    
    public boolean isCompleteWord() {
        return completeWord;
    }
}
