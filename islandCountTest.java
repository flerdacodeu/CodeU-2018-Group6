import static org.junit.Assert.*;

import org.junit.Test;

public class islandCountTest {

	// Set up of map
	public Boolean[][] mapSetUp() {
		
		Boolean map[][] = new Boolean[4][4];
		
		map[0][0] = false;
		map[0][1] = true;
		map[0][2] = false;
		map[0][3] = true;
		
		map[1][0] = true;
		map[1][1] = true;
		map[1][2] = false;
		map[1][3] = false;
		
		map[2][0] = false;
		map[2][1] = false;
		map[2][2] = true;
		map[2][3] = false;
		
		map[3][0] = false;
		map[3][1] = false;
		map[3][2] = true;
		map[3][3] = false;
		
		// F T F T
		// T T F F
		// F F T F
		// F F T F
		
		return map;
	}
	
	// Test correct number of trues become false
	@Test
	public void testSameIsland() {
		
		Boolean map[][] = mapSetUp();
		Islands world = new Islands();
		
		assertEquals(3, world.sameIslandCount(map, 0, 1));
		assertEquals(1, world.sameIslandCount(map, 0, 3));
		assertEquals(2, world.sameIslandCount(map, 3, 2));
		assertEquals(0, world.sameIslandCount(map, 3, 0));
		
	}
	
	// Test correct number of trues become false
	@Test
	public void testCountIsland() {
			
		Boolean map[][] = mapSetUp();
		Islands world = new Islands();
		
		assertEquals(3, world.islandsCount(map));
			
	}
	
	
}
