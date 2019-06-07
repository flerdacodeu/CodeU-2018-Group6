import static org.junit.Assert.*;

public class A4Q1 {

    public static void findIslandEnd(boolean[][] grid, int i, int j, int row, int column) {
        if (i < 0 || i >= row || j < 0 || j >= column) {
            return;
        }
        if (grid[i][j]) {
            //fload this cell and look for the and of the island
            grid[i][j] = false;
            findIslandEnd(grid, i - 1, j, row, column); //up
            findIslandEnd(grid, i + 1, j, row, column); // down
            findIslandEnd(grid, i, j - 1, row, column); // left
            findIslandEnd(grid, i, j + 1, row, column); // right

        }

    }

    public static int findIslands(boolean[][] grid) {
        int numOfIslands = 0;
        if (grid == null) {
            return numOfIslands;
        }
        int row = grid.length;
        int column = grid[0].length;

        //start looking for an island at any point
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j]) {
                    numOfIslands++;
                    findIslandEnd(grid, i, j, row, column);
                }
            }
        }
        return numOfIslands;
    }

    

    public static void testOneIsland() {
        boolean[][] grid = new boolean[][]{
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}
        };

        int result = findIslands(grid);
        assertEquals(1, result);
    }

    public static void testNoIsland() {
        boolean[][] grid = new boolean[][]{
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false}
        };

        int result = findIslands(grid);
        assertEquals(0, result);
    }

    public static void testRegular() {
        boolean[][] grid = new boolean[][]{
            {false, true, false, true},
            {true, true, false, false},
            {false, false, true, false},
            {false, false, true, false}
        };

        int result = findIslands(grid);
        assertEquals(3, result);
    }

    public static void testNoGrid() {
        boolean[][] grid = null;
        int result = findIslands(grid);
        assertEquals(0, result);
    }

    public static void testDiagonalIslands() {
        boolean[][] grid = new boolean[][]{
            {true, false, true},
            {false, true, false},
            {true, false, true}
        };
        int result = findIslands(grid);
        assertEquals(5, result);
    }

    public static void main(String[] args) {
        testRegular();
        testNoGrid();
        testOneIsland();
        testNoIsland();
        testDiagonalIslands();
    }
}