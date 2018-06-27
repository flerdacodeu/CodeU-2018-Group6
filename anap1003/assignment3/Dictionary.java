/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author Ana
 */
public class Dictionary {
    
    private TrieNode root;
    
    public Dictionary() {
        root = new TrieNode(null, false);
    }
    
    public void addWord(String word) {
        if (root == null) {
            root = new TrieNode(null, false);
        }
        
        TrieNode tempNode = root;
        word = word.toUpperCase();
        
        for (int i = 0; i < word.length(); i++) {
            if (tempNode.hasChild(word.charAt(i))) {
                tempNode = tempNode.moveToChild(word.charAt(i));
            } else {
                tempNode = tempNode.addChild(word.charAt(i), i == word.length() - 1);
            }
        }
        
    }
    
    public boolean isWord(String word) {
        TrieNode tempNode = dictionaryContainsWord(word);
        return tempNode != null && tempNode.isCompleteWord();
    }
    
    public boolean isPrefix(String prefix) {
        return dictionaryContainsWord(prefix) != null ;
    }
    
    private TrieNode dictionaryContainsWord(String word) {
        if (root == null) {
            throw new IllegalStateException("Unitialized digital tree.");
        }
        
        TrieNode tempNode = root;
        word = word.toUpperCase();
        
        for (int i = 0; tempNode != null && i < word.length(); i++) {
            tempNode = tempNode.moveToChild(word.charAt(i));
        }
        
        return tempNode;
    }
}
