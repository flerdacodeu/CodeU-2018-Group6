// Count the number of islands in the map using DFS search.
// Similar to finding the number of connected components in a graph,
// considering each land tile connected to the land tiles from up, down, left and right.
public class CountingIslands {

	// Check the provided map is of corresponding valid size.
	private static boolean checkMap(int rows, int columns, boolean[][] map){
		
		if(rows <= 0 || columns <= 0){
			return false;
		}
		
		if (map.length != rows)
			return false;
		
		for(int i = 0; i < rows; i++)
			if  (map[i].length != columns)
				return false;
		
		return true;
	}
	
	// Solution is based on DFS search from the True tiles not belonging to an island already.
	public static int countIslands(int rows, int columns, boolean[][] map){
		
		if (!checkMap(rows, columns, map)){
			throw new IllegalArgumentException("Invalid map");
		}
		
		// Keep track of the number of islands with count variable.
		// Use a visited boolean matrix to keep track of the visited tiles in the DFS. 
		int count = 0;
		boolean[][] visited = new boolean[rows][columns];
		
		// Every unvisited True (Land) tile is the start of a new island, so increase the counter
		// And do DFS search to mark visited the adjacent tiles belonging to the same island as the start.
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(map[i][j] && !visited[i][j]){
					count++;
					DFSutil(i, j, rows, columns, map, visited);
				}
			}
		}

		return count;
	}
	
	// Helper function to mark visited the tiles belonging to the same island
	// as the tile with cur_row and cur_col indices in the map.
	private static void DFSutil(int cur_row, int cur_col, int rows, int columns, boolean[][] map, boolean[][] visited){
		
		visited[cur_row][cur_col] = true;
		
		// Visit the neighbors of the current tile if their are valid to be visited.
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if (isValidTile(i, j, cur_row, cur_col, rows, columns, map, visited)){
					DFSutil(cur_row+i, cur_col+j, rows, columns, map, visited);
				}
			}			
		}		
	}
	
	// Helper function to check a neighbour tite of map[cur_row][cur_col] is valid to be visited.
	// A valid tile belongs to the same island as map[cur_row][cur_col].
	private static boolean isValidTile(int i, int j, int cur_row, int cur_col, int rows, int columns, boolean[][] map, boolean[][] visited){
		
		int next_row = i + cur_row; // next_row and next_col are the indices of the neighbour in the map
		int next_col = j + cur_col;
		
		if(next_row < 0 || next_col < 0) // check indices are not negative
			return false;
		if(next_row >= rows || next_col >= columns) // check indices are not bigger than size of map
			return false;
		if(i == j || i == j * (-1)) // don't allow diagonally adjacent tiles or the same tile as current (for i = j = 0)
			return false;
		if(visited[next_row][next_col]) // don't allow already visited tiles.
			return false;
		if(!map[next_row][next_col]) // don't allow False (Water) tiles of the map.
			return false;

		return true;
	}
}
