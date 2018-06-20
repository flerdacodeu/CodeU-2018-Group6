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
    private final int[] neighbourOffsetIArray = new int[] {-1, -1, -1, 0, 0, 1, 1, 1}, neighbourOffsetJArray = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
    
    /**
     * This methods finds all the valid words in a grid of letters, starting 
     * from every letter. A letter cannot be reused in a single word, and the 
     * valid directions of searching for a word are: horizontally, vertically 
     * and diagonally.
     * @param gridToSearch
     * @param dictionary
     * @return
     */
    public Set wordSearch(char[][] gridToSearch, Dictionary dictionary) {
        foundWords = new LinkedHashSet();
        
        grid = gridToSearch;
        rows = grid.length;
        cols = grid[0].length;
        BitSet visited = new BitSet(rows * cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                searchGridFromPosition(i, j, grid[i][j] + "", visited, dictionary);
            }
        }
        
        return foundWords;
    }
    
    /**
     * 
     * @param i row of the current letter that is being analyzed
     * @param j column of the current letter that is being analyzed
     * @param currentWord 
     * @param visited BitSet of the linearized grid, holding the information which letters have been visited in the current attempt to form a valid word
     * @param dictionary
     */
    private void searchGridFromPosition(int i, int j, String currentWord, BitSet visited, Dictionary dictionary) {
        if (dictionary.isWord(currentWord)) {
            foundWords.add(currentWord);
        }
        visited.set(i * cols + j); // recursively each attempted path sets its visited bit to true
        
        // attempting all possible directions of search from the current position
        for (int p = 0; p < neighbourOffsetIArray.length; p++) {
            continueSearchFromPosition(i + neighbourOffsetIArray[p], j + neighbourOffsetJArray[p], visited, dictionary, currentWord);
        }
        
        visited.set(i * cols + j, false); // once all possible paths from position (i,j) are exhausted, the bit is set to false
    }
    
    /**
     * Continues searching the grid if string (formed by concatenating the current word and the letter at position [k, p]) is a prefix
     */
    private void continueSearchFromPosition(int k, int t, BitSet visited, Dictionary dictionary, String currentWord) {
        if (isWithinBounds(k, t) && notVisited(k, t, visited)) {
            String suffix = grid[k][t] + "";
            if (dictionary.isPrefix(currentWord + suffix)) {
                searchGridFromPosition(k, t, currentWord + suffix, visited, dictionary);
            }
        }
    }
    
    private boolean isWithinBounds(int i, int j) {
        return i >=0 && i < rows && j >= 0 && j < cols;
    }
    
    private boolean notVisited(int i, int j, BitSet visited) {
        return !visited.get(i * cols + j);
    }
    
}
