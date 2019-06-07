/**
* You are given a 2-dimensional map of tiles. 
* Each tile is either land or water. 
* You have to write a function that counts the number of islands.
*
* @author  Argyro Gounari
* @version 1.0
* @since   01-07-2018 
*/
public class Islands {

	/**
	 * Function that counts the number of islands.
	 * False -> Water
	 * True -> Land
	*/
	public int islandsCount(Boolean[][] map) {
		
		int count = 0;
		for(int i=0; i<map.length; i++) {
	        for(int j=0; j<map[i].length; j++) {
	        	if (map[i][j] == true) {
					count++;
	        	}
	        	map = sameIsland(map, i, j);
	        }
	    }
		
		return count;
		
	}
	
	/**
	 * Two tiles belong to the same island 
	 * if they are both land and are adjacent 
	 * horizontally or vertically, but not diagonally.
	*/
	private Boolean[][] sameIsland(Boolean[][] map, int collum, int row) {
		if((row<0 || collum<0 || collum>=map.length || row>=map[collum].length) || map[collum][row]==false) 
			return map;
		
		map[collum][row] = false;
		sameIsland(map, collum, row-1); //above
		sameIsland(map, collum, row+1); //below
		sameIsland(map, collum-1, row); //left
		sameIsland(map, collum+1, row); //right
		return map;
		
	}
	
	/**
	 * Method to test sameIsland.
	 * Two tiles belong to the same island 
	 * if they are both land and are adjacent 
	 * horizontally or vertically, but not diagonally.
	*/
	public int sameIslandCount(Boolean[][] map, int collum, int row) {
		if((row<0 || collum<0 || collum>=map.length || row>=map[collum].length) || map[collum][row]==false) 
			return 0;
		
		map[collum][row] = false;
		int above = sameIslandCount(map, collum, row-1); //above
		int below = sameIslandCount(map, collum, row+1); //below
		int left = sameIslandCount(map, collum-1, row); //left
		int right = sameIslandCount(map, collum+1, row); //right
		return above + below + left + right + 1;
		
	}
	
}
