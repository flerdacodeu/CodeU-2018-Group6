package assignment3;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a grid of letters and a dictionary, find all the words from the dictionary that can be
 * formed in the grid.
 * The rules for forming a word:
 * - You can start at any position.
 * - You can move to one of the 8 adjacent cells (horizontally/vertically/diagonally).
 * - You can't visit the same cell twice in the same word.
 */
public class WordSearch {
    private Dictionary dict;
    private char[][] grid;
    private int rows;
    private int cols;

    /**
     * @param dict dictionary object that can be queried for valid words and prefixes
     * @param grid input grid
     */
    public WordSearch(Dictionary dict, char[][] grid) {
        this.dict = dict;
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
    }

    /**
     * @return set of words found in grid
     */
    public Set<String> findWords() {
        Set<String> results = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] visited = new boolean[rows][cols];
                visited[i][j] = true;
                searchGrid(i, j, results, grid[i][j] + "", visited);
            }
        }
        return results;
    }

    /**
     * Helper method to traverse all adjacent cells
     *
     * @param i       current row position
     * @param j       current column position
     * @param results set of words found
     * @param current word on current path
     * @param vis     cells visited on current traversal
     */
    private void searchGrid(int i, int j, Set<String> results, String current, boolean[][] vis) {
        if (dict.isWord(current)) {
            results.add(current);
        }
        for (int r = i - 1; r <= i + 1; r++) {
            for (int c = j - 1; c <= j + 1; c++) {
                if (!(r == i && c == j)) {
                    if (isValidPosition(r, c) && !vis[r][c]) {
                        String newWord = current + grid[r][c];
                        if (dict.isPrefix(newWord)) {
                            vis[r][c] = true;
                            searchGrid(r, c, results, newWord, vis);
                            vis[r][c] = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks given position is within grid
     *
     * @param i
     * @param j
     * @return true if position is valid
     */
    private boolean isValidPosition(int i, int j) {
        if (i < rows && j < cols && i >= 0 && j >= 0) {
            return true;
        }
        return false;
    }
}
