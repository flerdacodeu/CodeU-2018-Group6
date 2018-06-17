import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// Create a vertex class to represent the entries in the grid.
// Each vertex has the associated character and the position in the grid (line and column) and a visited value (for the dfs).
// For each vertex, store the vertices that can be reached from it (children), going in the 8 directions.
class Vertex{
	
	final char ch;
	final int line;
	final int column;
	ArrayList<Vertex> children = new ArrayList<Vertex>();
	boolean visited = false;
	
	Vertex(int line, int column, char ch){
		this.line = line;
		this.column = column;
		this.ch = ch;
	}
	
	public void addChild(Vertex v){
		children.add(v);
	}
	
	public void addChildren(List<Vertex> list){
		for(Vertex v: list)
			children.add(v);
	}
	
	public void setVisited(boolean value){
		visited = value;
	}
	
	public int getLine(){
		return line;
	}
	
	public int getColumn(){
		return column;
	}
	
	public ArrayList<Vertex> getChildren(){
		return children;
	}
	
	public char getChar(){
		return ch;
	}	
	
	public boolean wasVisited(){
		return visited;
	}
}

// A graph represented by its vertices.
// Vertices are stored in a matrix such that
// element at (i,j) is the corresponding Vertex of the cell at (i,j) in the given grid.
// Grid is an n x m matrix of chars.
class Graph{
	Vertex[][] vertices;
	int lines;
	int columns;
	
	// lines and columns are the size of the initial grid used to construct the representation.
	Graph(int lines, int columns){
		this.lines = lines;
		this.columns = columns;
		vertices = new Vertex[lines][columns];
	}
	
	// Create the vertices and the relation between them.
	// Return a matrix of Vertex objects.
	public Vertex[][] createVertices(char[][] grid){
		
		// Create a vertex for each cell in the grid.
		for(int i = 0; i < lines; i++){
			for(int j = 0; j < columns; j++){
				Vertex v = new Vertex(i, j, grid[i][j]);
				vertices[i][j] = v;
			}
		}

		addChildren();
		
		return vertices;
	}
	
	// Given position (i,j) of a vertex in vertices matrix, add its children (in the 8 directions).s
	public void addChildren(){
		for(int i = 1; i < lines - 1; i++)
			for(int j = 1; j < columns - 1; j++){
				vertices[i][j].addChild(vertices[i-1][j]); // up
				vertices[i][j].addChild(vertices[i-1][j-1]); // diagonal up left
				vertices[i][j].addChild(vertices[i-1][j+1]); // diagonal up right
				vertices[i][j].addChild(vertices[i+1][j]); // down
				vertices[i][j].addChild(vertices[i+1][j-1]); // diagonal down left
				vertices[i][j].addChild(vertices[i+1][j+1]); // diagonal down right
				vertices[i][j].addChild(vertices[i][j-1]); // left
				vertices[i][j].addChild(vertices[i][j+1]); // right	
		}
		
		// The four corners
		vertices[0][0].addChildren(Arrays.asList(vertices[0][1], vertices[1][0], vertices[1][1]));
		vertices[0][columns-1].addChildren(Arrays.asList(vertices[0][columns-2], vertices[1][columns-1], vertices[1][columns-2]));
		vertices[lines-1][0].addChildren(Arrays.asList(vertices[lines-2][0], vertices[lines-1][1], vertices[lines-2][1]));
		vertices[lines-1][columns-1].addChildren(Arrays.asList(vertices[lines-2][columns-1], vertices[lines-1][columns-2], vertices[lines-2][columns-2]));
	
	    // The rest of the borders
		for(int j = 1; j <= columns - 2; j++){
			vertices[0][j].addChild(vertices[0][j-1]);
		    vertices[0][j].addChild(vertices[0][j+1]);
		    vertices[0][j].addChild(vertices[1][j-1]);
		    vertices[0][j].addChild(vertices[1][j]);
		    vertices[0][j].addChild(vertices[1][j+1]);
		}
		
		for(int j = 1; j <= columns - 2; j++){
			vertices[lines-1][j].addChild(vertices[lines-2][j-1]);
		    vertices[lines-1][j].addChild(vertices[lines-2][j]);
		    vertices[lines-1][j].addChild(vertices[lines-2][j+1]);
		    vertices[lines-1][j].addChild(vertices[lines-1][j-1]);
		    vertices[lines-1][j].addChild(vertices[lines-1][j+1]);
		}
		
		for(int i = 1; i <= lines - 2; i++){
			vertices[i][0].addChild(vertices[i-1][0]);
		    vertices[i][0].addChild(vertices[i-1][1]);
		    vertices[i][0].addChild(vertices[i][1]);
		    vertices[i][0].addChild(vertices[i+1][0]);
		    vertices[i][0].addChild(vertices[i+1][1]);
		}
		
		for(int i = 1; i <= lines - 2; i++){
			vertices[i][columns-1].addChild(vertices[i-1][columns-2]);
		    vertices[i][columns-1].addChild(vertices[i-1][columns-1]);
		    vertices[i][columns-1].addChild(vertices[i][columns-2]);
		    vertices[i][columns-1].addChild(vertices[i+1][columns-2]);
		    vertices[i][columns-1].addChild(vertices[i+1][columns-1]);
		}	
	}
}

public class Q1 {

	// Checking the grid is the correct size
	public static boolean checkGrid(char[][] grid, int lines, int columns){
		if( lines <= 0 || columns <= 0){
			return false;
		}
		
		if (grid.length != lines)
			return false;
		
		for(int i = 0; i < lines; i++)
			if  (grid[i].length != columns)
				return false;
		
		return true;
	}
	
	// Create the vertices from the grid and use DFS from each through its children to form words.
	// lines and columns are the size of the grid, grid is an n x m matrix of chars.
	// Return an HashSet (to avoid duplicates) of the found words.
	public static HashSet<String> findWords(char[][] grid, Dictionary dict, int lines, int columns) throws Exception{
		
		if (!checkGrid(grid, lines, columns)){
			throw new IllegalArgumentException("Invalid grid");
		}
		
		HashSet<String> foundWords = new HashSet<String>(); 
		Graph graph = new Graph(lines, columns);
		Vertex[][] vertices = graph.createVertices(grid);
		
		for(int i = 0; i < lines; i++)
			for(int j = 0; j < columns; j++){
				
				// Check the word can start with the corresponding letter in the grid.
				String cur_prefix = grid[i][j] + "";
				
				if (dict.isPrefix(cur_prefix)){
					if(dict.isWord(cur_prefix)) // assume we can have one-letter words in dict.
						foundWords.add(cur_prefix);
					// DFS to form all the words from this current vertex.
					DFShelper(vertices[i][j], cur_prefix, dict, foundWords);
				}					
			}
		
		return foundWords;
	}

	// Recursive function for DFS traversal.
	private static void DFShelper(Vertex v, String prefix, Dictionary dict, HashSet<String> foundWords){
		
		// Mark the current vertex visited.
		v.setVisited(true);
		
		// Traverse the children of the current vertex.
		for(Vertex child: v.getChildren()){
			if(!child.wasVisited()){
				String new_prefix = prefix + child.getChar();
				// If we form an accepted prefix, keep adding characters with depth search.
				if(dict.isPrefix(new_prefix)){
					if(dict.isWord(new_prefix)) // If the prefix is also a word in dictionary, add it to final list.
						foundWords.add(new_prefix);
					DFShelper(child, new_prefix,  dict, foundWords);					
				}
			}			
		}
		v.setVisited(false);
	}
}
