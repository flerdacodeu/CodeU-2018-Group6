/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import assignment4.Assignment4;
import org.junit.Test;
import static org.junit.Assert.*;

public class NumberOfIslandsTest {
    private final Assignment4 islandsFinder = new Assignment4();
    private static boolean[][] sea;
    
    public NumberOfIslandsTest() {
    }
    
    @Test
    public void testGeneralCaseSeaOfIslands() {
        sea = new boolean[][]{
            {false, true, false, true},
            {true, true, false, false},
            {true, false, true, false},
            {false, false, true, false}
        };
        assertEquals(3, islandsFinder.findNumOfIslandsInSea(sea.length, sea[0].length, sea));
    }
    
    @Test
    public void testOneContinuousSnakelikeIsland() {
        sea = new boolean[][]{
            {true, true, true, false, true},
            {true, false, true, false, true},
            {true, false, true, true, true},
            {false, false, false, false, false}
        };
        assertEquals(1, islandsFinder.findNumOfIslandsInSea(sea.length, sea[0].length, sea));
    }
    
    @Test
    public void testIslandsWithinIsland() {
        sea = new boolean[][]{
            {true, true, true, true, true, true, true},
            {true, false, false, false, false, false, true},
            {true, false, true, false, true, false, true},
            {true, false, false, false, false, false, true},
            {true, true, true, true, true, true, true}
        };
        assertEquals(3, islandsFinder.findNumOfIslandsInSea(sea.length, sea[0].length, sea));
    }
    
    @Test
    public void testDiagonallySeparatedIslands() {
        sea = new boolean[][]{
            {true, false, true},
            {false, true, false},
            {true, false, true}
        };
        assertEquals(5, islandsFinder.findNumOfIslandsInSea(sea.length, sea[0].length, sea));
    }
    
    @Test
    public void testNoIslands() {
        sea = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
        };
        assertEquals(0, islandsFinder.findNumOfIslandsInSea(sea.length, sea[0].length, sea));
    }
    
    @Test
    public void testAllLand() {
        sea = new boolean[][]{
            {true, true, true},
            {true, true, true},
            {true, true, true}
        };
        assertEquals(1, islandsFinder.findNumOfIslandsInSea(sea.length, sea[0].length, sea));
    }
}
