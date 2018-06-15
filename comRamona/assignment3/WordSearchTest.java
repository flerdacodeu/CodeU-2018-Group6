package assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class WordSearchTest {
    Dictionary sampleDict;

    @Before
    public void setUp() {
        sampleDict = new Dictionary();
        sampleDict.addWord("CAR");
        sampleDict.addWord("CARD");
        sampleDict.addWord("CART");
        sampleDict.addWord("CAT");
    }

    @Test
    public void testSimpleCase() {
        char[][] grid = new char[][]{
                {'A', 'A', 'R'},
                {'T', 'C', 'D'}
        };
        WordSearch wordSearch = new WordSearch(sampleDict, grid);
        Set<String> result = wordSearch.findWords();
        Set<String> expected = new HashSet<>(Arrays.asList("CAR", "CARD", "CAT"));
        assertEquals(expected, result);
    }

    @Test
    public void testSameLetterTwiceInAWord() {
        char[][] grid = new char[][]{
                {'A', 'A', 'R'},
                {'T', 'C', 'D'}
        };
        sampleDict.addWord("ATA");  //letter A corresponds to different positions
        sampleDict.addWord("ARA"); //same position is not used twice
        WordSearch wordSearch = new WordSearch(sampleDict, grid);
        Set<String> result = wordSearch.findWords();
        Set<String> expected = new HashSet<>(Arrays.asList("CAR", "CARD", "CAT", "ATA"));
        assertEquals(expected, result);
    }

    @Test
    public void testValidWordContainedInAnotherValidWord() {
        char[][] grid = new char[][]{
                {'A', 'R', 'T'},
                {'C', 'X', 'X'}
        };
        WordSearch wordSearch = new WordSearch(sampleDict, grid);
        Set<String> result = wordSearch.findWords();
        Set<String> expected = new HashSet<>(Arrays.asList("CAR", "CART"));
        assertEquals(expected, result);
    }
}
