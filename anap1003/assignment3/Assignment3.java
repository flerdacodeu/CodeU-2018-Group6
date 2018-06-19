/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
public class Assignment3 {
    private int rows, cols;
    private Set<String> foundWords;
    private char[][] grid;
    
    /**
     * 
     * @param gridToSearch
     * @param dictionary
     * @return
     * @throws Exception 
     */
    public Set wordSearch(char[][] gridToSearch, Dictionary dictionary) throws Exception {
        foundWords = new LinkedHashSet();
        
        grid = gridToSearch;
        rows = grid.length;
        cols = grid[0].length;
        BitSet visited = new BitSet(grid.length * grid[0].length);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                searchGridFromPosition(i, j, String.valueOf(grid[i][j]), visited, dictionary);
            }
        }
        
        return foundWords;
    }
    
    /**
     * 
     * @param i
     * @param j
     * @param currentWord
     * @param visited
     * @param dictionary
     * @throws Exception 
     */
    private void searchGridFromPosition(int i, int j, String currentWord, BitSet visited, Dictionary dictionary) throws Exception {
        if (dictionary.isWord(currentWord)) {
            foundWords.add(currentWord);
        }
        visited.set(i * cols + j); // recursively each attempted path sets its visited bit to true
        
        // attempting all possible directions of search from the current position
        continueSearchFromPosition(i-1, j-1, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i-1, j, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i-1, j+1, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i, j-1, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i, j+1, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i+1, j-1, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i+1, j, visited, dictionary, currentWord);
        
        continueSearchFromPosition(i+1, j+1, visited, dictionary, currentWord);
        
        visited.set(i * cols + j, false); // once all possible paths from position (i,j) are exhausted, the bit is set to false
    }
    
    /**
     * 
     * @param k
     * @param t
     * @param visited
     * @param dictionary
     * @param currentWord
     * @throws Exception 
     */
    private void continueSearchFromPosition(int k, int t, BitSet visited, Dictionary dictionary, String currentWord) throws Exception {
        if (isWithinBounds(k, t) && notVisited(k, t, visited)) {
            String suffix = String.valueOf(grid[k][t]);
            if (dictionary.isPrefix(currentWord + suffix)) {
                searchGridFromPosition(k, t, currentWord + suffix, visited, dictionary);
            }
        }
    }
    
    /**
     * 
     * @param i
     * @param j
     * @return 
     */
    private boolean isWithinBounds(int i, int j) {
        return i >=0 && i < rows && j >= 0 && j < cols;
    }
    
    /**
     * 
     * @param i
     * @param j
     * @param visited
     * @return 
     */
    private boolean notVisited(int i, int j, BitSet visited) {
        return !visited.get(i * cols + j);
    }
    
}
