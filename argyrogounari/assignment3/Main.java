/**
* Given a grid of letters and a dictionary, find all the words from the dictionary that can be formed in the grid.
* The rules for forming a word:
* You can start at any position.
* You can move to one of the 8 adjacent cells (horizontally/vertically/diagonally).
* You can't visit the same cell twice in the same word.
*
* @author  Argyro Gounari
* @version 1.0
* @since   12-06-2018 
*/
public class Main {

	public static void main(String[] args) {
		
		Dictionary theDictionary = new Dictionary();
		
		char grid[][] = new char[2][3];
		grid[0][0] = 'A';
		grid[0][1] = 'A';
		grid[0][2] = 'R';
		grid[1][0] = 'T';
		grid[1][1] = 'C';
		grid[1][2] = 'D';
		// A A R
		// T C D
		
		wordsFormed(theDictionary, grid);
		
	}
	
	// Print words in grid
	public static void wordsFormed(Dictionary theDictionary, char[][] grid) {
		
		String str = "";
		Boolean seen[][] = new Boolean[2][3];
		wordsFormed(theDictionary, grid, seen, 0, 0, str);
		
	}
		
	// Print words in grid
	public static void wordsFormed(Dictionary theDictionary, char[][] grid, Boolean[][] seen, int i, int j, String str) {
		
        seen[i][j] = true;
        str = str + grid[i][j];
        
		if (theDictionary.isWord(str)){
			System.out.println(str);
		}
		
		for (int row=i-1; row<=i+1 && row<grid.length; row++) {
		      for (int col=j-1; col<=j+1 && col<grid.length; col++) {
		        if (row>=0 && col>=0 && !seen[row][col]) {
		          wordsFormed(theDictionary,grid, seen, row, col, str);
		        }
		      }  
		}
		
		str = str.substring(0, str.length()-1);
		seen[i][j] = false;
		
	}
	

}
