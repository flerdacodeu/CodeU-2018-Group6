package assignment4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberOfIslandsTest {
    private static final Boolean F = false;
    private static final Boolean T = true;

    @Test
    public void testNoIslands() {
        boolean[][] grid = new boolean[][]{
                {F, F, F},
                {F, F, F},
                {F, F, F}
        };
        int expected = 0;
        int actual = NumberOfIslands.countIsland(grid);
        assertEquals(expected, actual);
    }

    @Test
    public void testEntireGridIsOneIsland() {
        boolean[][] grid = new boolean[][]{
                {T, T, T},
                {T, T, T},
                {T, T, T}
        };
        int expected = 1;
        int actual = NumberOfIslands.countIsland(grid);
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleIslands() {
        boolean[][] grid = new boolean[][]{
                {F, T, F, T},
                {T, T, F, F},
                {F, F, T, F},
                {F, F, T, F}
        };
        int expected = 3;
        int actual = NumberOfIslands.countIsland(grid);
        assertEquals(expected, actual);
    }

    @Test
    public void testDiagonalTilesAreDifferentIslands() {
        //multiple one element islands on the diagonal
        boolean[][] grid = new boolean[][]{
                {T, F, F, F},
                {F, T, F, F},
                {F, F, T, F},
                {F, F, F, T}
        };
        int expected = 4;
        int actual = NumberOfIslands.countIsland(grid);
        assertEquals(expected, actual);
    }

}
