/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import assignment3.*;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 */
public class WordSearchTest {
    private Dictionary testDictionary;
    private char[][] grid;
    
    @Before
    public void setUp() {
        testDictionary = new Dictionary();
        testDictionary.addWord("CAR");
        testDictionary.addWord("CARD");
        testDictionary.addWord("CART");
        testDictionary.addWord("CAT");
        
        grid = new char[2][3];
        grid[0][0] = 'A';
        grid[0][1] = 'A';
        grid[0][2] = 'R';
        grid[1][0] = 'T';
        grid[1][1] = 'C';
        grid[1][2] = 'D';
    }
    
    @Test
    public void testFoundWords() throws Exception {
        Assignment3 wordSearcher = new Assignment3();
        Set<String> foundWords = wordSearcher.wordSearch(grid, testDictionary);
        
        Assert.assertEquals(true, foundWords.contains("CAR"));
        Assert.assertEquals(true, foundWords.contains("CARD"));
        Assert.assertEquals(true, foundWords.contains("CAT"));
        
        Assert.assertEquals(false, foundWords.contains("CART"));
    }
    
}
