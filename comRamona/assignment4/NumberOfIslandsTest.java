package assignment4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberOfIslandsTest {
    @Test
    public void testNoIslands() {
        boolean[][] grid = new boolean[][]{
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };
        int expected = 0;
        int actual = NumberOfIslands.countIsland(3, 3, grid);
        assertEquals(expected, actual);
    }

    @Test
    public void testEntireGridIsOneIsland() {
        boolean[][] grid = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        int expected = 1;
        int actual = NumberOfIslands.countIsland(3, 3, grid);
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleIslands() {
        boolean[][] grid = new boolean[][]{
                {false, true, false, true},
                {true, true, false, false},
                {false, false, true, false},
                {false, false, true, false}
        };
        int expected = 3;
        int actual = NumberOfIslands.countIsland(4, 4, grid);
        assertEquals(expected, actual);
    }

    @Test
    public void testDiagonalTilesAreDifferentIslands() {
        //multiple one element islands on the diagonal
        boolean[][] grid = new boolean[][]{
                {true, false, false, false},
                {false, true, false, false},
                {false, false, true, false},
                {false, false, false, true}
        };
        int expected = 4;
        int actual = NumberOfIslands.countIsland(4, 4, grid);
        assertEquals(expected, actual);
    }

}
