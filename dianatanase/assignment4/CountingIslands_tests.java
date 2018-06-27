import static org.junit.Assert.*;

import org.junit.Test;

public class CountingIslands_tests {
	
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidRowSize() throws Exception {
		
		boolean[][] map = {{false, true},
					{true, false}};
		
		CountingIslands.countIslands(3,2,map);
	}	

	@Test
	public void test_smallMap_allWaterTiles() {
		boolean[][] map = {{false, false},
					{false, false}};
		
	assertEquals(CountingIslands.countIslands(2,2,map),0);
	}
	
	@Test
	public void test_smallMap_allLandTiles() {
		boolean[][] map = {{true, true},
					{true, true}};
		
	assertEquals(CountingIslands.countIslands(2,2,map),1);
	}	
	
	@Test
	public void test_smallMap_waterAndLandTiles() {
		boolean[][] map = {{false, true},
				{true, false}};
		
	assertEquals(CountingIslands.countIslands(2,2,map),2);
	}
	
	// No diagonally adjacent Land tiles.
	@Test
	public void test_mediumMap_contiguousHorizontalLandTiles() {  // A way to visualise the islands:
		boolean[][] map = {{false, false, false, false},      // 0 0 0 0
				{true, true, true, false},                    // 1 1 1 0
				{false, false, false, false},                 // 0 0 0 0
				{false, true, true, true}};                   // 0 2 2 2
		
	assertEquals(CountingIslands.countIslands(4,4,map),2);
	}
	
	// No diagonally adjacent Land tiles.
	@Test
	public void test_mediumMap_contiguousVerticalLandTiles() {
		boolean[][] map = {{false, true, false, false},        // 0 1 0 0
				{false, true, false, true},                    // 0 1 0 2
				{false, true, false, true},                    // 0 1 0 2
				{false, false, false, true}};                  // 0 0 0 2
		
	assertEquals(CountingIslands.countIslands(4,4,map),2);
	}
	
	// Each Land tile diagonally adjacent to another one.
	@Test
	public void test_mediumMap_diagonalLandTiles() {
		boolean[][] map = {{true, false, true, false},       // 1 0 2 0
				{false, true, false, true},                  // 0 3 0 4
				{true, false, true, false},                  // 5 0 6 0
				{false, true, false, true}};                 // 0 7 0 8
		
	assertEquals(CountingIslands.countIslands(4,4,map),8);
	}
	
	// Next three tests have various placements of Land tiles
	// Including diagonally, horizontally and vertically adjacent Land tiles.
	@Test
	public void test_mediumMap_complexIslands1() {
		boolean[][] map = {{false, true, false, true},    // 0 1 0 2
				{true, true, false, false},               // 1 1 0 0 
				{false, false, true, false},              // 0 0 3 0
				{false, false, true, false}};             // 0 0 3 0
		
	assertEquals(CountingIslands.countIslands(4,4,map),3);
	}
		
	@Test
	public void test_mediumMap_complexIslands2() {
		boolean[][] map = {{false, true, false, true},     // 0 1 0 2
				{true, false, false, false},               // 3 0 0 0
				{true, true, true, false},                 // 3 3 3 0
				{true, false, false, true}};               // 3 0 0 4
		
	assertEquals(CountingIslands.countIslands(4,4,map),4);
	}
	
	@Test
	public void test_mediumMap_complexIslands3() {
		boolean[][] map = {{false, true, false, true},    // 0 1 0 2
				{true, false, false, true},               // 3 0 0 2
				{false, true, true, false},               // 0 4 4 0
				{false, false, false, true}};             // 0 0 0 5
		
	assertEquals(CountingIslands.countIslands(4,4,map),5);
	}

	@Test
	public void test_biggerMap_compelxIslands() {
		boolean[][] map = {{false, true, false, true, false, false},   // 0 1 0 2 0 0 
				{true, false, false, false, true, false},              // 3 0 0 0 4 0
				{false, true, true, false, false, true},               // 0 5 5 0 0 6
				{false, false, false, true, true, false},              // 0 0 0 7 7 0
				{true, true, false, true, true, true}};                // 8 8 0 7 7 7
		
	assertEquals(CountingIslands.countIslands(5,6,map),8);
	}
}
