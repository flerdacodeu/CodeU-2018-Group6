package assignment4;

/**
 * You are given a 2-dimensional map of tiles. Each tile is either land or water. You have to write a function that
 * counts the number of islands. Two tiles belong to the same island if they are both land and are adjacent horizontally
 * or vertically, but not diagonally.The input to your function is the number of rows, number of columns, and a
 * 2-dimensional array of booleans, where false means water and true means land. The function should simply return the
 * number of islands.
 */
public class NumberOfIslands {

    /**
     * Computes the number of islands in a grid of tiles
     *
     * @param rows    number of rows
     * @param columns number of columns
     * @param grid    given grid of tiles
     * @return number of islands
     */
    public static int countIsland(int rows, int columns, boolean[][] grid) {
        int count = 0;
        boolean[][] visited = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] && !visited[i][j]) {
                    visitIsland(i, j, grid, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private static void visitIsland(int row, int column, boolean[][] grid, boolean[][] visited) {
        if (!isValidPosition(row, column, grid.length, grid[0].length) || visited[row][column] || !grid[row][column]) {
            return;
        }
        visited[row][column] = true;
        visitIsland(row - 1, column, grid, visited);
        visitIsland(row + 1, column, grid, visited);
        visitIsland(row, column + 1, grid, visited);
        visitIsland(row, column - 1, grid, visited);

    }

    private static boolean isValidPosition(int i, int j, int rows, int columns) {
        return i >= 0 && j >= 0 && i < rows && j < columns;
    }
}
