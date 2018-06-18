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
        boolean dictionaryContainsPrefix = true;
        word = word.toUpperCase();
        
        for (int i = 0; i < word.length(); i++) {
            if (dictionaryContainsPrefix && tempNode.hasChild(word.charAt(i))) {
                tempNode = tempNode.moveToChild(word.charAt(i));
            } else {
                dictionaryContainsPrefix = false;
                tempNode = tempNode.addChild(word.charAt(i), i == word.length() - 1);
            }
        }
        
    }
    
    public boolean isWord(String word) throws Exception {
        TrieNode tempNode = dictionaryContainsWord(word);
        return tempNode != null && tempNode.isCompleteWord();
    }
    
    public boolean isPrefix(String prefix) throws Exception {
        return dictionaryContainsWord(prefix) != null ;
    }
    
    private TrieNode dictionaryContainsWord(String word) throws Exception {
        if (root == null) {
            throw new Exception("Unitialized digital tree.");
        }
        
        TrieNode tempNode = root;
        word = word.toUpperCase();
        
        for (int i = 0; tempNode != null && i < word.length(); i++) {
            tempNode = tempNode.moveToChild(word.charAt(i));
        }
        
        return tempNode;
    }
}
