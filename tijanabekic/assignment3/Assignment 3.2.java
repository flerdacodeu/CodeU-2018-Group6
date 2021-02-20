package A3Q1;

import java.util.HashSet;
import java.util.LinkedList;
import static org.junit.Assert.*;


public class A3Q1 {
    public static Dictionary dictionary;
    public static void checkWord(char[][] grid, int i, int j, String start, int m, int n, HashSet<String> words,  boolean[][] visited) {
        
        //make the string for checking
        start =  start + grid[i][j];
        
        
        //if the string is not a prefix, it can never form a word
        if (dictionary.isPrefix(start) == false) {
            return;
        }
        //mark the cell visited
        visited[i][j] = true;
        //add to result if it is a word
        if (dictionary.isWord(start)) {
            //by using a set we can eliminate duplicats
            
            words.add(start);
            
        }
        //check if it is possible to make a word in any direction
        for (int i1= i-1; i1 <=i+1; i1++)
            for (int j1=j-1; j1<=j+1;j1++)
                //check if it the same letter and if it is out of bounds
                if (!(i1==i && j1==j) && i1>=0 && i1<m && j1>=0 && j1<n)
                    //check if it is already visited
                    if(visited[i1][j1] == false)
                        checkWord(grid, i1, j1, start, m, n, words, visited);
        
        visited[i][j] = false;
        return;
    }

    public static HashSet<String> findWords(char[][] grid) {
        
        if (grid == null) {
            return null;
        }
        int m = grid.length;
        int n = grid[0].length;
        //store all the results in a list
        LinkedList<String> result = new LinkedList<String>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        //make a map so there is no need to double check strings
       
        HashSet<String> words = new HashSet<String>();
        //start at any point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String start = "";
                checkWord(grid, i, j, start, m, n, words, visited);
            }
        }
        
        
        return words;
    }

    public static void testRegular() {
        int m = 2;
        int n = 3;
        char[][] grid = new char[][] {
            {'A', 'A', 'R'},
            {'T', 'C', 'D'}
        };
        
        HashSet<String> result = findWords(grid);
        HashSet<String> expected = new HashSet<>();
        expected.add("CAR");
        expected.add("CARD");
        expected.add("CAT");
        assertEquals(expected, result);
        
        return;
    }

    public static void testNoWords() {
        int m = 2;
        int n = 3;
        char[][] grid = new char[][] {
            {'A', 'A', 'A'},
            {'A', 'A', 'A'}
        };
        HashSet<String> result = findWords(grid);
        HashSet<String> expected = new HashSet<>();
        assertEquals(expected, result);
        
        return;
    }

    public static void testNullGrid() {
        int m = 3;
        int n = 2;
        char[][] grid = null;
        HashSet<String> result = findWords(grid);
        assertEquals(null, result);
       
        return;
    }

    public static void main(String[] args) {
        dictionary = new Dictionary();
        testRegular();       
        testNoWords();
        testNullGrid();
    }
}